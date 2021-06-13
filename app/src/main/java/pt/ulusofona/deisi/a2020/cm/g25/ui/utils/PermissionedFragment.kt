package pt.ulusofona.deisi.a2020.cm.g25.ui.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment

abstract class PermissionedFragment(private val requestCode: Int) : Fragment() {

    fun onRequestedPermissions(context: Context, permissions: Array<String>) {
        var permissionsGiven = 0
        permissions.forEach {
            if (checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, requestCode)
            } else {
                permissionsGiven++
            }
        }
        if (permissionsGiven == permissions.size) {
            onRequestedPermissionsSucess()
        }
    }

    abstract fun onRequestedPermissionsSucess()
    abstract fun onRequestedPermissionsFailure()
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (this.requestCode == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onRequestedPermissionsSucess()
            }else{
                onRequestedPermissionsFailure()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}