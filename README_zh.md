### 使用前提 ###
* 确保主机上已安装GTK3
* 有关安装的详细信息，请参阅Kotlin/Native -Gtk官方示例

### 运行示例 ###
* Macos与Linux平台
    * 在项目目录打开终端，运行以下命令：
   ```shell script
   gradlew run
   ```
* Windows平台
    * 由于gtk3是MSYS2的开发环境，应用程序应该在`MINGW64`工具中打开。
    * 示例`demo`构建脚本定义了 `mingwDist`任务，运行任务它将会在程序目录生成一个`mingwDist`脚本，
    并且它将打开`MINGW64`工具运行这个脚本，脚本用于分析程序运行所依赖的动态库并将其复制到程序目录，
    如果你将`MSYS2`定制安装在其他位置，在执行该任务之前，确保系统已设置`MSYS2_DIR`和`MINGW64_DIR`
    环境变量，现在，你可以运行它：
   ```shell script
   gradlew run
   ```