import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.18"
}

fluidLibrary(name = "stdlib", version = "0.10.4")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.9.5-kotlin-1.5"))
			}
		}

		darwin()
		jvm()
	}
}
