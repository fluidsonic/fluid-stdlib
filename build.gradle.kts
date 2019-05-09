import com.github.fluidsonic.fluid.library.*

plugins {
	id("com.github.fluidsonic.fluid-library") version "0.9.10"
}

fluidLibrary {
	name = "fluid-stdlib"
	version = "0.9.5"
}

fluidLibraryVariant {
	description = "Potentially useful Kotlin standard library additions"
	jdk = JDK.v1_7
}

kotlin {
	sourceSets {
		jvmMain {
			dependencies {
				implementation("org.threeten:threetenbp:1.4.0")
			}
		}
	}
}
