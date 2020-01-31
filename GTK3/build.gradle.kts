import org.jetbrains.kotlin.konan.target.*

plugins {
    kotlin("multiplatform")
}

kotlin {
    val native = when (true) {
        HostManager.hostIsMac -> macosX64("native")
        HostManager.hostIsLinux -> linuxX64("native")
        HostManager.hostIsMingw -> mingwX64("native")
        else -> throw Throwable("Unsupported system host: ${HostManager.hostName}")
    }
    native.apply {
        compilations["main"].cinterops.create("gtk3")
        binaries.staticLib(listOf(RELEASE))
    }
}