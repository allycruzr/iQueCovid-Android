package pt.ulusofona.deisi.a2020.cm.g25.model.sensors.battery

import android.content.Context
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
import java.lang.ref.WeakReference
import android.content.Intent

import android.content.IntentFilter
import android.os.Looper
import android.os.HandlerThread
import androidx.appcompat.app.AppCompatActivity


class Battery private constructor(private val context: WeakReference<Context>) : Runnable {
    private val TIME_BETWEEN_UPDATES = 2 * 1000L


    companion object {
        private var instance: Battery? = null
        private var bgThread: HandlerThread = HandlerThread("Battery Thread")
        private lateinit var handler: Handler
        var listener: OnBatteryCurrentListener? = null

        fun start(context: Context, onBatteryPercentageListener: OnBatteryCurrentListener) {
            if(instance != null) return
            bgThread.start()
            handler = Handler(bgThread.looper)
            listener = onBatteryPercentageListener
            instance = if (instance == null) Battery(WeakReference(context)) else instance
            instance?.start()
        }
    }

    private fun start() {
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

    override fun run() {
        val current = getBatteryCurrentNow()
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)

        (context.get() as? AppCompatActivity)?.runOnUiThread {
            listener?.onCurrentChanged(current)
        }
    }

    private fun getBatteryCurrentNow(): Double {
        val batteryIntent = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = context.get()?.registerReceiver(null, batteryIntent) ?: return -1.0

        val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

        return level / scale.toDouble()
    }
}