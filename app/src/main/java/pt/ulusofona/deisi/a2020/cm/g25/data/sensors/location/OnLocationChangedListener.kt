package pt.ulusofona.deisi.a2020.cm.g25.data.sensors.location

import com.google.android.gms.location.LocationResult

interface OnLocationChangedListener {
    fun onLocationChangedListener(locationResult: LocationResult)
}