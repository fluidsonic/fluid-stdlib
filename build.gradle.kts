import com.github.fluidsonic.fluid.library.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.19"
}

fluidLibrary {
	name = "fluid-stdlib"
	version = "0.9.22"
}

fluidLibraryVariant {
	description = "Potentially useful Kotlin standard library additions"

	jvm(JDK.v1_7)
	objc()
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				api(fluid("time", "0.9.11"))

				implementation(kotlinx("serialization-runtime-common", "0.11.1"))
			}
		}

		iosArm64Main {
			dependencies {
				implementation(kotlinx("serialization-runtime-iosarm64", "0.11.1"))
			}
		}

		iosX64Main {
			dependencies {
				implementation(kotlinx("serialization-runtime-iosx64", "0.11.1"))
			}
		}

		jvmMain {
			dependencies {
				implementation(kotlinx("serialization-runtime", "0.11.1"))
				implementation("org.threeten:threetenbp:1.4.0")
			}
		}

		macosX64Main {
			dependencies {
				implementation(kotlinx("serialization-runtime-macosx64", "0.11.1"))
			}
		}
	}
}
