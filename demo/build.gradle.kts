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
        compilations["main"].defaultSourceSet {
            dependencies {
                implementation(project(":GTK3"))
            }
        }
        binaries.executable {
            entryPoint = "leung.demo.main"
        }
    }
}

tasks {
    register("run") {
        group = "run"
        dependsOn("mingwDist", "runReleaseExecutableNative")

        container["mingwDist"].mustRunAfter("linkReleaseExecutableNative")
    }

    register("mingwDist") {
        group = "run"
        dependsOn("linkReleaseExecutableNative")
        enabled = HostManager.hostIsMingw
        doFirst {
            val msysPath = System.getenv("MSYS2_DIR") ?: "c:/msys64"
            val msysShell = file(msysPath).resolve("msys2_shell.cmd")
            val exeDir = container["linkReleaseExecutableNative"].outputs.files.first()
            val mingwDist = exeDir.resolve("mingwDist")
            if (mingwDist.exists().not()) {
                mingwDist.createNewFile()
                val script = """|#!/usr/bin/env bash
                                |
                                |CUR_DIR=`cd ${'$'}(dirname ${'$'}0);pwd`
                                |
                                |if [[ "${'$'}MSYSTEM" == "MINGW64" ]]; then
                                |  APP_NAME=`find ${'$'}CUR_DIR -maxdepth 1 -name "*.exe"`
                                |  if [[ ${'$'}{#APP_NAME} != 0 ]]; then
                                |    for l in `ldd ${'$'}APP_NAME | grep mingw | sed "s/=.*//g"`; do
                                |      if [[ ${'$'}{#l} != 0 ]]; then
                                |        cp -u -p "/mingw64/bin/${'$'}l" "${'$'}CUR_DIR/"
                                |        cp -u -p "/mingw64/bin/gdbus.exe"  "${'$'}CUR_DIR/"
                                |      fi
                                |    done
                                |    echo -e "\033[32mDone.\033[0m"
                                |  else
                                |    echo -e "\033[31mWarning: *.exe executables are not found in the current directory. Please copy the distribution tool to the application directory.\033[0m"
                                |  fi
                                |else
                                |  echo -e "\033[31mError: Distribution tools need to be used in the MSYS MINGW64 environment.\033[0m"
                                |fi
                                """.trimMargin()
                mingwDist.outputStream().writer().apply {
                    write(script)
                    flush()
                }.close()
            }
            if (exeDir.listFiles()!!.none { it.extension == "dll" }) {
                exec {
                    commandLine(
                        msysShell,
                        "-mingw64",
                        mingwDist
                    )
                }
            }
        }
    }

}
