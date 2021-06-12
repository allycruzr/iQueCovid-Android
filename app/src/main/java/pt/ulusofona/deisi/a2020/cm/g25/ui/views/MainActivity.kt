package pt.ulusofona.deisi.a2020.cm.g25.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.sensors.battery.Battery
import pt.ulusofona.deisi.a2020.cm.g25.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g25.repository.Repository
import pt.ulusofona.deisi.a2020.cm.g25.ui.NavigationManager

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.goToDashBoardFragment(supportFragmentManager)

        val botNavBAr: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        // var mainToolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.main_toolbar)

        var toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        // TODO: Rui Moreira -> Continuar aqui! https://www.youtube.com/watch?v=sBCE_hOFnQU&t=269s
        val repository = Repository()
        //val viewModelFactory = MainViewModelFactory(repository)
        //viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCounties()
        //viewModel.myResponse.observe(this, Observer)


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
}