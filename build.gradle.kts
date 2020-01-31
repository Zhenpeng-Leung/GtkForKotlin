buildscript {
    val kotlinVersion: String by project

    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
    }
}

subprojects {
    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/public")
    }
    buildDir = file("${rootProject.buildDir}/$name")
}