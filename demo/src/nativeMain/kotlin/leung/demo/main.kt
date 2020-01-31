package leung.demo

import gtk3.extensions.Application
import gtk3.extensions.Button
import gtk3.extensions.ButtonBox
import gtk3.extensions.Window

fun main(args: Array<String>) {
    val app = Application("leung.demo")
    app.onActivity {
        val window = Window(app.ptr)

        val buttonBox = ButtonBox()
        window.add(buttonBox)

        val button = Button("Click Me!")
        button.onClicked {
            println("Hello Kotlin")
        }
        buttonBox.add(button)

        window.show()
    }
    println("exit code: " + app.run(args))
}