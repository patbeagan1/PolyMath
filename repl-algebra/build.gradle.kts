import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    `maven-publish`
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
    
    js {
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
            implementation(kotlin("stdlib"))
            api(project(":math-algebra"))
        }
        
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        
        jsMain.dependencies {
            // JS-specific dependencies if needed
        }
        
        jvmMain.dependencies {
            // JVM-specific dependencies if needed
        }
    }
}
