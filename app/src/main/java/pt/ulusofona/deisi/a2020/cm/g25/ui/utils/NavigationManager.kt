package pt.ulusofona.deisi.a2020.cm.g25.ui.utils

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.dashboard.DashboardFragment
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.ui.adapters.CountiesAdapter
import pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.testlist.TestListFragment
import pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.extra.ExtraFragment
import pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.contacts.ContactsFragment
import pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.counties.CountiesFragment
import pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.settings.SettingsFragment

abstract class NavigationManager {

    companion object {

        val dashboardFragment = DashboardFragment()
        val testListFragment = TestListFragment()
        val countiesFragment = CountiesFragment()
        val extraFragment = ExtraFragment()
        val contactsFragment = ContactsFragment()
        val settingsFragment = SettingsFragment()

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

        fun goToCountiesFragment(fm: FragmentManager) {
            placeFragment(fm, countiesFragment)
        }

        fun goToVaccinationFragment(fm: FragmentManager) {
            placeFragment(fm, extraFragment)
        }

        fun goToContactsFragment(fm: FragmentManager) {
            placeFragment(fm, contactsFragment)
        }


        fun goToSettingsFragment(fm: FragmentManager) {
            placeFragment(fm, settingsFragment)
        }
    }
}