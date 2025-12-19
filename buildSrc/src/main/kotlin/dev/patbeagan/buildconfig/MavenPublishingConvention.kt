package dev.patbeagan.buildconfig

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories

class MavenPublishingConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply the maven publish plugin
            plugins.apply("com.vanniktech.maven.publish")
            
            // Configure repositories
            repositories {
                mavenCentral()
            }
            
            // The maven publish plugin will be configured by the individual modules
            // This convention plugin just applies the plugin and sets up basic structure
        }
    }
}