package pt.ulusofona.deisi.a2020.cm.g25.data.sensors.battery


import android.content.Context
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
import java.sql.BatchUpdateException


@Suppress("DEPRECATION")
class Battery private constructor(private val context: Context) : Runnable {
    private val TAG = Battery::class.java.simpleName
    private val TIME_BETWEEN_UPDATES = 20 * 1000L

    companion object {
        private var instance: Battery? = null
        private val handler = Handler()

        fun start(context: Context) {
            instance = if (instance == null) Battery(context) else instance
            instance?.start()

        }
    }

    private fun start() {
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

    override fun run() {
        val current = getBatteryCurrentNow()
        Log.i(TAG,current.toString())
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

    private fun getBatteryCurrentNow(): Double {
        val manager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val value:Int = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        return if (value !=0 && value != Int.MIN_VALUE) value.toDouble() / 1000000 else 0.0
    }

}