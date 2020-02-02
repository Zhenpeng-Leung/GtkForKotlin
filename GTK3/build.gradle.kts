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
        compilations["main"].cinterops.create("gtk3") {
            when (true) {
                HostManager.hostIsMac, HostManager.hostIsLinux -> {
                    listOf("/opt/local/include", "/usr/include", "/usr/local/include").forEach {
                        includeDirs(
                            "$it/atk-1.0",
                            "$it/gdk-pixbuf-2.0",
                            "$it/cairo",
                            "$it/harfbuzz",
                            "$it/pango-1.0",
                            "$it/gtk-3.0",
                            "$it/glib-2.0"
                        )
                    }

                    includeDirs(
                        "/opt/local/lib/glib-2.0/include",
                        "/usr/lib/x86_64-linux-gnu/glib-2.0/include",
                        "/usr/local/lib/glib-2.0/include"
                    )
                }
                HostManager.hostIsMingw -> {
                    val mingwPath= file(System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")
                    listOf(
                        "include/atk-1.0",
                        "include/gdk-pixbuf-2.0",
                        "include/cairo",
                        "include/pango-1.0",
                        "include/gtk-3.0",
                        "include/glib-2.0",
                        "lib/glib-2.0/include"
                    ).forEach {
                        includeDirs(mingwPath.resolve(it))
                    }
                }
            }
        }
        binaries.staticLib(listOf(RELEASE))
    }
}