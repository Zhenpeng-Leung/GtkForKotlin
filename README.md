### Use the premise ###
* make sure GTK3 is installed on the host
* for more information about the installation, see the official Kotlin/ native-gtk example
### Run the example ###
* Macos and Linux platforms
    * open the terminal in the project directory and run the following command:
    ```shell script
    gradlew run
    ```
* Windows platform
    * since gtk3 is the development environment for MSYS2, the application should open in the `MINGW64` tool.
    * first go to the project directory and run the following command:
    ```shell script
    gradlew run
    ```
    * at this point, the application is prompted for an exception exit because the system lacks the libraries needed to run the GTK+ application.
    * open the `MINGW64` tool, enter the `$GtkForKotlin\build\demo\bin\native\releaseExecutable` directory, and run the following command:
    ```shell script
    ./demo.exe
    ```
    * you should be able to see that the program is working.
    * `$GtkForKotlin` directory has a Windows distribution tool, `mingwDist`, that copies the tools to the program directory
    `$GtkForKotlin\build\demo\bin\native\releaseExecutable`, which also requires the `MINGW64` tool to run,
    It copies the `*.dll` library needed to run the program to the program directory. Now, you can double-click to run the program.
    * or now you can run it using `gradlew run`.