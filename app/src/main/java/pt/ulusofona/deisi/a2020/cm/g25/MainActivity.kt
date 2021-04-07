package pt.ulusofona.deisi.a2020.cm.g25

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2020.cm.g25.fragments.DashboardFragment
import pt.ulusofona.deisi.a2020.cm.g25.navigation.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g25.views.ContactsFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.goToDashBoardFragment(supportFragmentManager)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_dashboard -> {
                NavigationManager.goToDashBoardFragment(supportFragmentManager)
                return true
            }
            R.id.nav_test_list -> {
              NavigationManager.goToTestListFragment(supportFragmentManager)
              return true
            }
            R.id.nav_extraPage -> {
              NavigationManager.goToVaccinationFragment(supportFragmentManager)
              return true
            }
            R.id.nav_contacts -> {
                NavigationManager.goToContactsFragment(supportFragmentManager)
                return true
            }
        }
        return false
    }
}