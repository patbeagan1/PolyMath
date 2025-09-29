import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform") version "2.2.20"
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
    js(IR).nodejs()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("test"))
        }
    }
}