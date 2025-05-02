pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // JitPack dihapus karena tidak dipakai lagi
        // maven(url = "https://jitpack.io")
    }
}
rootProject.name = "matematika_cer"
include(":app")
