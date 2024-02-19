package dev.patbeagan.buildconfig

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests

class DebugPrintPlugins : Plugin<Project> {
    override fun apply(target: Project) {
        debug(target)
    }

    private fun debug(target: Project) {
        println()
        target.plugins.forEach { println(it) }
        println()
    }
}

class ApplyCommonKMPSettings : Plugin<Project> {
    override fun apply(target: Project) {
        target.getKotlinPluginVersion()
            .also(::println)
    }
}

//fun KotlinMultiplatformExtension.getNativeTarget(): KotlinNativeTargetWithHostTests {
//    val hostOs = System.getProperty("os.name")
//    val isMingwX64 = hostOs.startsWith("Windows")
//    return when {
//        hostOs == "Mac OS X" -> macosX64("native")
//        hostOs == "Linux" -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
//        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
//    }
//}
//
//fun KotlinMultiplatformExtension.applyCommonKMPForJVM() {
//    jvm {
//        compilations.all {
//            kotlinOptions.jvmTarget = "17"
//        }
//        testRuns["test"].executionTask.configure {
//            useJUnitPlatform()
//        }
//    }
//}
//
//fun KotlinMultiplatformExtension.applyCommonKMPForJS() {
//    js(IR) {
//        browser {
//            commonWebpackConfig {
////                cssSupport  {
////                    enabled = true
////                }
//            }
//        }
//    }
//}