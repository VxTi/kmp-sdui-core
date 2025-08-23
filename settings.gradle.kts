rootProject.name = "sdui-cmp-poc"

include("app")
include("sdui-common")
include("sdui")

project(":sdui").projectDir = File(rootDir, "packages/sdui/")
project(":sdui-common").projectDir = File(rootDir, "packages/sdui-common/")
project(":app").projectDir = File(rootDir, "packages/app/")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}