package pt.ulusofona.deisi.a2020.cm.g25.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.a2020.cm.g25.views.dashboard.DashboardFragment
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.views.testlist.TestListFragment
import pt.ulusofona.deisi.a2020.cm.g25.views.extra.ExtraFragment
import pt.ulusofona.deisi.a2020.cm.g25.views.contacts.ContactsFragment

abstract class NavigationManager {

    companion object {

        val dashboardFragment = DashboardFragment()
        val testListFragment = TestListFragment()
        val extraFragment = ExtraFragment()
        val contactsFragment = ContactsFragment()

        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            val transition = fm.beginTransaction()
            transition
                .replace(R.id.content_main, fragment)
                .addToBackStack(null)
                .commit()
        }

        fun goToDashBoardFragment(fm: FragmentManager) {
            placeFragment(fm, dashboardFragment)
        }

        fun goToTestListFragment(fm: FragmentManager) {
            placeFragment(fm, testListFragment)
        }

        fun goToVaccinationFragment(fm: FragmentManager) {
            placeFragment(fm, extraFragment)
        }

        fun goToContactsFragment(fm: FragmentManager) {
            placeFragment(fm, contactsFragment)
        }
    }
}