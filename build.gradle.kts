import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.0"
}

fluidLibrary(name = "stdlib", version = "0.10.0")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	targets {
		common {
			dependencies {
				api(fluid("time", "0.10.0"))

				implementation(kotlinx("serialization-runtime", "1.0-M1-1.4.0-rc"))
			}
		}

		jvm()

		jvmJdk7 {
			dependencies {
				implementation("org.threeten:threetenbp:1.4.4")
			}
		}

		nativeDarwin()
	}
}
