package gtk3.extensions

import gtk3.GClosureNotify
import gtk3.GConnectFlags
import gtk3.g_signal_connect_data
import gtk3.gpointer
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toLong

@ThreadLocal
object Signal {
    val handlers = mutableMapOf<Long, () -> Unit>()
}

fun <F : CFunction<*>> signalConnect(obj: CPointer<*>,
                                     actionName: String,
                                     action: CPointer<F>,
                                     data: gpointer? = null,
                                     destroyData: GClosureNotify? = null,
                                     connectFlags: GConnectFlags = 0u) {
    g_signal_connect_data(obj.reinterpret(), actionName, action.reinterpret(), data, destroyData, connectFlags)
}


fun signalHandler(obj: CPointer<*>) {
    Signal.handlers[obj.toLong()]?.invoke()
}