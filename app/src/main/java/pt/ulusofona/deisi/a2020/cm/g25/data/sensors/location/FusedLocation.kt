package pt.ulusofona.deisi.a2020.cm.g25.data.sensors.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class FusedLocation private constructor(val context: Context) : LocationCallback() {
    private val TAG = FusedLocation::class.java.simpleName

    // intervalo de tempo em que a localização é verificada, 20 segundos
    private val TIME_BETWEEN_UPDATES = 20 * 1000L

    // este atributo é usado para configurar os pedidos de localização
    private var locationRequest: LocationRequest? = null

    // este atributo sera utilizado para acedermos à API da fused location
    private var client = FusedLocationProviderClient(context)

    init {
        // Configurar a precião e os tempos entre as atualizações
        locationRequest = LocationRequest()
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest?.interval = TIME_BETWEEN_UPDATES
        
        // Instanciar o objeto que permite definir as configurações
        val locationSettingsRequest =
            LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!).build()
        
        //Aplicarr as configuraçõeos ao serviço de localização
        LocationServices.getSettingsClient(context).checkLocationSettings(locationSettingsRequest)
    }

    companion object {
        private var listener: OnLocationChangedListener? = null
        private var instance: FusedLocation? = null

        fun registerListener(onLocationChangedListener: OnLocationChangedListener) {
            this.listener = onLocationChangedListener
        }

        fun unregisterListener() {
            listener = null
        }

        fun notifyListeners(locationResult: LocationResult) {
            listener?.onLocationChangedListener(locationResult)
        }

        // Só termos uma instância em execuçãoe
        fun start(context: Context) {
            instance = if(instance==null) FusedLocation(context) else instance
            instance?.startLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        client.requestLocationUpdates(locationRequest, this, Looper.myLooper())
    }

    override fun onLocationResult(locationResult: LocationResult?) {
        Log.i(TAG, "Location ->${locationResult?.lastLocation.toString()}")
        locationResult?.let{ notifyListeners(it)}
        super.onLocationResult(locationResult)
    }
}