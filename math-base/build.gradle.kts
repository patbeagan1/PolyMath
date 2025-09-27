plugins {
    kotlin("multiplatform") version "1.9.20"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js().nodejs()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("test"))
            implementation(projects.unitBase)
        }
    }
}
