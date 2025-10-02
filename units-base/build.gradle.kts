import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlinx.binary-compatibility-validator")
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
            implementation(kotlin("test"))
        }
        commonTest.dependencies {
            implementation(project(":units-common"))
        }
    }
}
