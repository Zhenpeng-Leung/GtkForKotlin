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
    * 首先进入项目目录，运行以下命令：
   ```shell script
   gradlew run
   ```
  * 这时候会提示程序异常退出，这是因为系统缺少运行gtk+程序所需要的库导致的。
  * 打开`MINGW64`工具，进入`$GtkForKotlin\build\demo\bin\native\releaseExecutable`目录，运行以下命令：
  ```shell script
  ./demo.exe
  ```
  * 你应该可以看到程序正常运行了。
  * `$GtkForKotlin`目录有一个Windows平台的分发工具`mingwDist`，将工具复制到程序目录
  `$GtkForKotlin\build\demo\bin\native\releaseExecutable`，它同样需要`MINGW64`工具来运行,
  它会将程序运行所需要的`*.dll`库文件复制到程序目录，现在，你可以双击运行程序了。
  * 或者现在你可以使用`gradlew run`运行它。