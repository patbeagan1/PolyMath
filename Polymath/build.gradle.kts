//import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
//import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    `maven-publish`
    application
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
        create<MavenPublication>("regex-builder") {
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

//fun KotlinMultiplatformExtension.getNativeTarget(): KotlinNativeTarget {
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
//}

//subprojects {
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
//}