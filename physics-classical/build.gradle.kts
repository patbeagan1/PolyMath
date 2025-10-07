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
    js(IR).nodejs()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("test"))
            api(projects.unitsBase)
            api(projects.mathAlgebra)
        }
    }
}
