import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "3.0.0"
}

fluidLibrary(name = "stdlib", version = "0.15.0")

fluidLibraryModule(description = "Potentially useful Kotlin standard library additions") {
	custom {
		compilerOptions {
			freeCompilerArgs.add("-Xexpect-actual-classes")
		}
	}

	targets {
		common {
			dependencies {
				implementation(fluid("locale", "0.14.0"))
			}
		}

		@Suppress("DEPRECATION")
		js()
		jvm()
	}
}
