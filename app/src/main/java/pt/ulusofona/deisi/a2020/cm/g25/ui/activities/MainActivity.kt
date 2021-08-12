package pt.ulusofona.deisi.a2020.cm.g25.ui.activities

import pt.ulusofona.deisi.a2020.cm.g25.ui.utils.Constants.Companion.REQUEST_CODE
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.LocationResult
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.sensors.battery.Battery
import pt.ulusofona.deisi.a2020.cm.g25.data.sensors.battery.OnBatteryCurrentListener
import pt.ulusofona.deisi.a2020.cm.g25.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g25.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.MainInterface
import pt.ulusofona.deisi.a2020.cm.g25.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity(), MainInterface, OnBatteryCurrentListener, OnLocationChangedListener {

    private lateinit var viewModel: MainViewModel

    private lateinit var botNavBAr: BottomNavigationView

    private var msgWasShowed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        NavigationManager.goToDashBoardFragment(supportFragmentManager)

        Battery.start(this, this)
        FusedLocation.start(this)

        botNavBAr = findViewById(R.id.bottom_navigation_bar)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.title = getString(R.string.activity_dashboard_name)
        botNavBAr.menu.findItem(R.id.nav_dashboard).setChecked(true)

        toolbar.inflateMenu(R.menu.top_app_bar)

        //supportActionBar?.setTitle(R.string.activity_dashboard_name)

        onRequestedPermissions(
            this.baseContext!!, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )

        toolbar.setOnMenuItemClickListener {
            NavigationManager.goToSettingsFragment(supportFragmentManager)
            toolbar.title = getString(R.string.settings_title)
            it.setChecked(true)
            true
        }

        botNavBAr.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_dashboard -> {
                    NavigationManager.goToDashBoardFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_dashboard_name)
                    it.setChecked(true)
                }
                R.id.nav_test_list -> {
                    NavigationManager.goToTestListFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_list_tests_name)
                    it.setChecked(true)
                }
                R.id.nav_counties -> {
                    NavigationManager.goToCountiesFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_counties_name)
                    it.setChecked(true)
                }
                R.id.nav_extraPage -> {
                    NavigationManager.goToVaccinationFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.symptoms)
                    it.setChecked(true)
                }
                R.id.nav_contacts -> {
                    NavigationManager.goToContactsFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_contacts_name)
                    it.setChecked(true)
                }
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
    }

    override fun onCurrentChanged(current: Double) {
        if (!msgWasShowed){
            if (current <= 20){
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                //val toast = Toast.makeText(this, getText(R.string.toast_battery), Toast.LENGTH_LONG)
                //toast.show()
                //msgWasShowed = true
            }
        }
    }

    override fun onLocationChangedListener(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        val gcd = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses.size > 0) {
            viewModel.checkDangerZone(addresses[0].adminArea)
        }
    }

    fun onRequestedPermissionsSucess() {
        FusedLocation.registerListener(this)
    }

    fun onRequestedPermissions(context: Context, permissions: Array<String>) {
        var permissionsGiven = 0
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(
                    context,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(permissions, REQUEST_CODE)
            } else {
                permissionsGiven++
            }
        }
        if (permissionsGiven == permissions.size) {
            onRequestedPermissionsSucess()
        }
    }

    override fun checkDangerZoneValue(checkDangerZone: String) {
        when (checkDangerZone) {
            "Baixo a Moderado" -> toolbar.setNavigationIcon(R.drawable.ic_checked)
            "Moderado" -> toolbar.setNavigationIcon(R.drawable.ic_warning)
            "Elevado" -> toolbar.setNavigationIcon(R.drawable.ic_danger)
            "Muito Elevado" -> toolbar.setNavigationIcon(R.drawable.ic_extreme_danger)
        }
    }
}