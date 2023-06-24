import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.3.1"
}

fluidLibrary(name = "stdlib", version = "0.14.0")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.13.0"))
			}
		}
		darwin()
		jvm()
	}
}
