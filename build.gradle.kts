import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


description = "Potentially useful Kotlin standard library additions"
group = "com.github.fluidsonic"
version = "0.9.2"


plugins {
	kotlin("jvm") version "1.3.20"
	`java-library`
	maven
	`maven-publish`
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

val javadoc = tasks["javadoc"] as Javadoc
val javadocJar by tasks.creating(Jar::class) {
	archiveClassifier.set("javadoc")
	from(javadoc)
}

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")
	from(sourceSets["main"].allSource)
}

publishing {
	publications {
		create<MavenPublication>("default") {
			from(components["java"])
			artifact(sourcesJar)
		}
	}
}

val ossrhUserName = findProperty("ossrhUserName") as String?
val ossrhPassword = findProperty("ossrhPassword") as String?
if (ossrhUserName != null && ossrhPassword != null) {
	artifacts {
		add("archives", javadocJar)
		add("archives", sourcesJar)
	}

	signing {
		sign(configurations.archives.get())
	}

	tasks {
		"uploadArchives"(Upload::class) {
			repositories {
				withConvention(MavenRepositoryHandlerConvention::class) {
					mavenDeployer {
						withGroovyBuilder {
							"beforeDeployment" { signing.signPom(delegate as MavenDeployment) }

							"repository"("url" to "https://oss.sonatype.org/service/local/staging/deploy/maven2/") {
								"authentication"("userName" to ossrhUserName, "password" to ossrhPassword)
							}

							"snapshotRepository"("url" to "https://oss.sonatype.org/content/repositories/snapshots/") {
								"authentication"("userName" to ossrhUserName, "password" to ossrhPassword)
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
}
