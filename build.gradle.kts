import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.10"
}

fluidLibrary(name = "stdlib", version = "0.10.3")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.9.3"))
			}
		}

		darwin()
		jvm()
	}
}
