package pt.ulusofona.deisi.a2020.cm.g25.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.views.ContactsActivity
import pt.ulusofona.deisi.a2020.cm.g25.views.TestListActivity

class DashboardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container:ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.dashboard_page -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.test_list_page -> {
                    startActivity(Intent(activity, TestListActivity::class.java))
                    true
                }
                R.id.extraPage -> {
                    //startActivity(Intent(this, ExtraActivity::class.java))

                    true
                }
                R.id.contacts_page -> {
                    startActivity(Intent(activity, ContactsActivity::class.java))
                    true
                }
                else -> false
            }
        }
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onStart() {
        super.onStart()

        button_contacts.setOnClickListener {
            startActivity(Intent(activity, ContactsActivity::class.java))
        }

        button_testes.setOnClickListener {
            startActivity(Intent(activity, TestListActivity::class.java))
        }
    }
}