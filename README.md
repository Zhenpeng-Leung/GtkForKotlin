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
    * since gtk3 is the development environment for `MSYS2`, the application should open in the `MINGW64` tool.
    * The sample `demo` build script defines the `mingwDist` task, which will generate a `mingwDist` script in 
    the program directory when it runs the task, and it will open the `MINGW64` tool to run the script. 
    The script is used to analyze the dynamic libraries on which the program runs and copy it to the program directory.
    If you have the `MSYS2` custom installed somewhere else, make sure the system has the `MSYS2_DIR` and `MINGW64_DIR`
     environment variables set before performing this task. Now you can run it:
    ```shell script
    gradlew run
    ```