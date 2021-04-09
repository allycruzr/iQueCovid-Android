package pt.ulusofona.deisi.a2020.cm.g25

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.deisi.a2020.cm.g25.navigation.NavigationManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.goToDashBoardFragment(supportFragmentManager)

        val botNavBAr: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        // var mainToolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.main_toolbar)

        toolbar.title = getString(R.string.activity_dashboard_name)

        //setSupportActionBar(mainToolbar)
        supportActionBar?.setTitle(R.string.activity_dashboard_name)

        botNavBAr.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_dashboard -> {
                    NavigationManager.goToDashBoardFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_dashboard_name)
                    //botNavBAr.selectedItemId = R.id.nav_dashboard
                    true
                }
                R.id.nav_test_list -> {
                    NavigationManager.goToTestListFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_list_tests_name)
                    //botNavBAr.selectedItemId = R.id.nav_test_list
                    true
                }
                R.id.nav_extraPage -> {
                    NavigationManager.goToVaccinationFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.symptoms)
                    //botNavBAr.selectedItemId = R.id.nav_extraPage
                    true
                }
                R.id.nav_contacts -> {
                    NavigationManager.goToContactsFragment(supportFragmentManager)
                    toolbar.title = getString(R.string.activity_contacts_name)
                    //botNavBAr.selectedItemId = R.id.nav_contacts
                    true
                }
            }
            false
        }
    }
}