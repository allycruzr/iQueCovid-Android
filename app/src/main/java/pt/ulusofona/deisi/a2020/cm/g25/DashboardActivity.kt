package pt.ulusofona.deisi.a2020.cm.g25

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.dashboard_page -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.test_list_page -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()

        button_contacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        button_testes.setOnClickListener {
            startActivity(Intent(this, TestListActivity::class.java))
        }

        button_form.setOnClickListener {
            startActivity(Intent(this, TestFormActivity::class.java))
        }
    }
}