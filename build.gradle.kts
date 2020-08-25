import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.8"
}

fluidLibrary(name = "stdlib", version = "0.10.1")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.9.2"))
			}
		}

		darwin()
		jvm()
	}
}
