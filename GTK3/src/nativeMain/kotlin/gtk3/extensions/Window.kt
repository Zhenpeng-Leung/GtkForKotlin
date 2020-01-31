package gtk3.extensions

import gtk3.*
import kotlinx.cinterop.*

class Window(app: CPointer<GtkApplication>?, name: String = "NewWindow",
             width: Int = 200, height: Int = 200) : Container() {
    override val widgetPtr = gtk_application_window_new(app)!!
    override val self = widgetPtr.reinterpret<GtkWindow>()

    var title: String
        get() = gtk_window_get_title(self).toString()
        set(value) = gtk_window_set_title(self, value)

    var windowSize: Pair<Int, Int>
        get() = memScoped {
            val w = nativeHeap.alloc<IntVar>().ptr
            val h = nativeHeap.alloc<IntVar>().ptr
            gtk_window_get_size(self, w, h)
            w.pointed.value to h.pointed.value
        }
        set(value) = gtk_window_set_default_size(self, value.first, value.second)


    fun show() = gtk_widget_show_all(widgetPtr)

    init {
        title = name
        windowSize = width to height
    }
}