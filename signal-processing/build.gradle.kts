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
    js(IR).nodejs()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("stdlib"))
            api(projects.unitsBase)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
