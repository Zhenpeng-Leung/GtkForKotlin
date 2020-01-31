package gtk3.extensions

import gtk3.GtkButtonBox
import gtk3.GtkOrientation
import gtk3.gtk_button_box_new
import kotlinx.cinterop.reinterpret

class ButtonBox(orientation: GtkOrientation = GtkOrientation.GTK_ORIENTATION_HORIZONTAL) : Container() {
    override val widgetPtr = gtk_button_box_new(orientation)!!
    override val self = widgetPtr.reinterpret<GtkButtonBox>()
}