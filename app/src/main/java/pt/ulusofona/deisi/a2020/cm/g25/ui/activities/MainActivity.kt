package pt.ulusofona.deisi.a2020.cm.g25.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.sensors.battery.OnBatteryCurrentListener
import pt.ulusofona.deisi.a2020.cm.g25.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), OnBatteryCurrentListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.goToDashBoardFragment(supportFragmentManager)

        val botNavBAr: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        var toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        toolbar.title = getString(R.string.activity_dashboard_name)

        //setSupportActionBar(mainToolbar)
        supportActionBar?.setTitle(R.string.activity_dashboard_name)

        botNavBAr.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_dashboard -> {
                    NavigationManager.goToDashBoardFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_dashboard_name)
                    it.setChecked(true)
                    true
                }
                R.id.nav_test_list -> {
                    NavigationManager.goToTestListFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_list_tests_name)
                    it.setChecked(true)
                     true
                }
                R.id.nav_extraPage -> {
                    NavigationManager.goToVaccinationFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.symptoms)
                    toolbar.setNavigationIcon(R.drawable.ic_warning)
                    it.setChecked(true)
                    true
                }
                R.id.nav_contacts -> {
                    NavigationManager.goToContactsFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_contacts_name)
                    it.setChecked(true)
                    true
                }
            }
            false
        }
    }

    override fun onCurrentChanged(current: Double) {
        if (current <= 20){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            val toast = Toast.makeText(this, getText(R.string.toast_battery), Toast.LENGTH_LONG)
            toast.show()
        }
    }
}