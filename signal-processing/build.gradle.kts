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
    js().nodejs()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("stdlib"))
            api(project(":units-base"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
