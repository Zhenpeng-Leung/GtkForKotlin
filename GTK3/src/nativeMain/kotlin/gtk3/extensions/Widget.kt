package gtk3.extensions

import gtk3.GtkWidget
import gtk3.gtk_widget_destroy
import kotlinx.cinterop.CPointer

abstract class Widget {
    abstract val widgetPtr: CPointer<GtkWidget>
    fun destroy() = gtk_widget_destroy(widgetPtr)
}