import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.2.1"
}

fluidLibrary(name = "stdlib", version = "0.12.0")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.12.0"))
			}
		}
		darwin()
		jvm()
	}
}
