package gtk3.extensions

import gtk3.gtk_container_add
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

abstract class Container: Widget() {
    abstract val self: CPointer<*>
    fun add(widget: Widget) = gtk_container_add(self.reinterpret(), widget.widgetPtr)
}