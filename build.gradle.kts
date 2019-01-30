import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


description = "Potentially useful Kotlin standard library additions"
group = "com.github.fluidsonic"
version = "0.9.0"


plugins {
	kotlin("jvm") version "1.3.20"
	`java-library`
	maven
	signing
	id("com.github.ben-manes.versions") version "0.20.0"
}

sourceSets {
	getByName("main") {
		java.setSrcDirs(emptyList())
		kotlin.setSrcDirs(listOf("sources"))
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
	withType<KotlinCompile> {
		sourceCompatibility = "1.8"
		targetCompatibility = "1.8"

		kotlinOptions.jvmTarget = "1.8"
	}

	withType<Wrapper> {
		distributionType = Wrapper.DistributionType.ALL
		gradleVersion = "5.2-rc-1"
	}
}

dependencies {
	api(kotlin("stdlib-jdk8"))
}

repositories {
	mavenCentral()
	jcenter()
}

val SourceSet.kotlin
	get() = withConvention(KotlinSourceSet::class) { kotlin }


// publishing

val sonatypeUserName = findProperty("sonatypeUserName") as String?
val sonatypePassword = findProperty("sonatypePassword") as String?
if (sonatypeUserName != null && sonatypePassword != null) {
	val javadocJar by tasks.creating(Jar::class) {
		archiveClassifier.set("javadoc")
		from(tasks["javadoc"])
	}

	val sourcesJar by tasks.creating(Jar::class) {
		archiveClassifier.set("sources")
		from(sourceSets["main"].allSource)
	}

	artifacts {
		archives(javadocJar)
		archives(sourcesJar)
	}

	signing {
		sign(configurations.archives.get())
	}

	tasks.getByName<Upload>("uploadArchives") {
		repositories {
			withConvention(MavenRepositoryHandlerConvention::class) {
				mavenDeployer {
					beforeDeployment {
						signing.signPom(this)
					}

					withGroovyBuilder {
						"repository"("url" to "https://oss.sonatype.org/service/local/staging/deploy/maven2") {
							"authentication"("userName" to sonatypeUserName, "password" to sonatypePassword)
						}

						"snapshotRepository"("url" to "https://oss.sonatype.org/content/repositories/snapshots") {
							"authentication"("userName" to sonatypeUserName, "password" to sonatypePassword)
						}
					}

					pom.project {
						withGroovyBuilder {
							"name"(project.name)
							"description"(project.description)
							"packaging"("jar")
							"url"("https://github.com/fluidsonic/fluid-stdlib")
							"developers" {
								"developer" {
									"id"("fluidsonic")
									"name"("Marc Knaup")
									"email"("marc@knaup.io")
								}
							}
							"licenses" {
								"license" {
									"name"("Apache License 2.0")
									"url"("https://github.com/fluidsonic/fluid-stdlib/blob/master/LICENSE")
								}
							}
							"scm" {
								"connection"("scm:git:https://github.com/fluidsonic/fluid-stdlib.git")
								"developerConnection"("scm:git:git@github.com:fluidsonic/fluid-stdlib.git")
								"url"("https://github.com/fluidsonic/fluid-stdlib")
							}
						}
					}
				}
			}
		}
	}
}
