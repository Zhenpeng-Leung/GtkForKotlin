package gtk3.extensions

import gtk3.G_APPLICATION_FLAGS_NONE
import gtk3.g_application_run
import gtk3.g_object_unref
import gtk3.gtk_application_new
import kotlinx.cinterop.*
import kotlin.native.concurrent.isFrozen

class Application(id: String) {
    val ptr = gtk_application_new(id, G_APPLICATION_FLAGS_NONE)!!

    fun onActivity(action: () -> Unit) {
        if (!Signal.isFrozen) Signal.handlers[ptr.toLong()] = action
    }

    fun run(args: Array<String>): Int {
        val status = memScoped {
            g_application_run(ptr.reinterpret(), args.size, args.map { it.cstr.ptr }.toCValues())
        }
        g_object_unref(ptr)
        return status
    }

    init {
        signalConnect(ptr, "activate", staticCFunction(::signalHandler))
    }
}