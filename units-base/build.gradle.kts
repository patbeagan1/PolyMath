import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlinx.binary-compatibility-validator")
    id("dev.patbeagan.mavenPublishingConvention")
}

group = "io.github.patbeagan1"
version = "1.0-SNAPSHOT"

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
    js(IR) {
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
            implementation(kotlin("test"))
        }
        commonTest.dependencies {
            implementation(project(":units-common"))
        }
    }
}

// Maven publishing configuration
mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    
    coordinates(group.toString(), name, version.toString())
    
    pom {
        name.set(project.name)
        description.set("PolyMath ${project.name} - A comprehensive units and measures library")
        inceptionYear.set("2025")
        url.set("https://github.com/patbeagan1/PolyMath/")
        
        licenses {
            license {
                name.set("MIT")
            }
        }
        
        developers {
            developer {
                id.set("patbeagan1")
                name.set("patbeagan1")
                url.set("https://github.com/patbeagan1/")
            }
        }
        
        scm {
            url.set("https://github.com/patbeagan1/PolyMath/")
            connection.set("scm:git:git://github.com/patbeagan1/PolyMath.git")
            developerConnection.set("scm:git:ssh://git@github.com/patbeagan1/PolyMath.git")
        }
    }
}
