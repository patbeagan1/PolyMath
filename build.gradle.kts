// import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
// import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    `maven-publish`
    application
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("org.jetbrains.kotlin.multiplatform") version "2.2.20" apply false
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.18.1" apply false
}

group = "dev.patbeagan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("polymath") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "myRepo"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

// Binary compatibility validator is now applied to individual modules

// Ktlint configuration
ktlint {
    version.set("1.7.1")
    android.set(false)
    ignoreFailures.set(false)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

// Detekt configuration
detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/detekt.yml")
}

// fun KotlinMultiplatformExtension.getNativeTarget(): KotlinNativeTarget {
//    val hostOs = System.getProperty("os.name")
//    val isArm64 = System.getProperty("os.arch") == "aarch64"
//    val isMingwX64 = hostOs.startsWith("Windows")
//    val nativeTarget = when {
//        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
//        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
//        hostOs == "Linux" && isArm64 -> linuxArm64("native")
//        hostOs == "Linux" && !isArm64 -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
//        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
//    }
//    return nativeTarget
// }

// subprojects {
//    if (hasProperty("multiplatform")) {
//        kotlin {
//            jvm {
//                jvmToolchain(17)
//                withJava()
//                testRuns.named("test") {
//                    executionTask.configure {
//                        useJUnitPlatform()
//                    }
//                }
//            }
//            js {
//                browser {
//                    commonWebpackConfig {
//                        cssSupport {
//                            enabled.set(true)
//                        }
//                    }
//                }
//            }
//            getNativeTarget()
//            sourceSets {
//                val commonMain by getting
//                val commonTest by getting {
//                    dependencies {
//                        implementation(kotlin("test"))
//                    }
//                }
//                val jvmMain by getting
//                val jvmTest by getting
//                val jsMain by getting
//                val jsTest by getting
//                val nativeMain by getting
//                val nativeTest by getting
//            }
//        }
//    }
// }
