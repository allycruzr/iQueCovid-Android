package pt.ulusofona.deisi.a2020.cm.g25.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.a2020.cm.g25.fragments.DashboardFragment
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.views.ContactsFragment

abstract class NavigationManager {

    companion object {
        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.main_container, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToDashBoardFragment(fm: FragmentManager) {
            placeFragment(fm, DashboardFragment())
        }

        fun goToContactsFragment(fm: FragmentManager) {
            placeFragment(fm, ContactsFragment())
        }
    }
}