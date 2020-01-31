package gtk3.extensions

import gtk3.gtk_button_new_with_label
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toLong
import kotlin.native.concurrent.isFrozen

class Button(label: String) : Widget() {
    override val widgetPtr = gtk_button_new_with_label(label)!!

    fun onClicked(action: () -> Unit) {
        if (!Signal.isFrozen) Signal.handlers[widgetPtr.toLong()] = action
    }

    init {
        signalConnect(widgetPtr, "clicked", staticCFunction(::signalHandler))
    }
}