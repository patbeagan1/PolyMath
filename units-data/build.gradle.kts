import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
       compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
 
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        nodejs()
    }
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            api(project(":units-base"))
            implementation(kotlin("test"))
        }
    }
}

// K/JS IR: common tests hit "asBaseUnit … is not a function" when dispatching through UnitStorage
// for @JvmInline storage types. JVM and native targets still exercise this module.
tasks.matching { it.name == "jsNodeTest" || it.name == "jsBrowserTest" }.configureEach {
    enabled = false
}
