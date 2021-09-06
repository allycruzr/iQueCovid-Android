package pt.ulusofona.deisi.a2020.cm.g25.model.sensors.battery

interface OnBatteryCurrentListener {
    fun onCurrentBatteryChanged(current: Double)
}