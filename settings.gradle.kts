pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://dl.bintray.com/fluidsonic/maven")
	}
}

rootProject.name = "fluid-stdlib"

include("jdk8")

project(":jdk8").name = "fluid-stdlib-jdk8"
