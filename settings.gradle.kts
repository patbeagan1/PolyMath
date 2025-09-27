pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "tex-builder"

include(":lib-graph")
include(":math-base")
include(":math-algebra")
include(":math-geometry")
include(":physics-classical")
include(":unit-base")
include(":unit-common")
include(":latex-builder")
