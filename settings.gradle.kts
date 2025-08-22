include(":app")
include(":sdui-common")
include(":sdui")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "sdui-cmp-poc"
project(":sdui").projectDir = File(rootDir, "packages/sdui/")
project(":sdui-common").projectDir = File(rootDir, "packages/sdui-common/")
project(":app").projectDir = File(rootDir, "packages/app/")