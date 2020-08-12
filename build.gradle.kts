import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.4"
}

fluidLibrary(name = "stdlib", version = "0.10.1")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.9.0"))

				implementation(kotlinx("serialization-runtime", "1.0-M1-1.4.0-rc"))
			}
		}

		jvmJdk7()
		nativeDarwin()
	}
}
