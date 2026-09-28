pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "tex-builder"

// include(":lib-graph")  // Temporarily disabled due to configuration issues
include(":math-base")
include(":math-algebra")
include(":math-geometry")
include(":physics-classical")
include(":signal-processing")
include(":units-base")
include(":units-common")
include(":units-data")
include(":latex-builder")
include(":repl-algebra")
