package pt.ulusofona.deisi.a2020.cm.g25

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    override fun onStart() {
        super.onStart()

        button_contacts.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
            finish()
        }

        button_testes.setOnClickListener {
            //startActivity(Intent(this, ContactsActivity::class.java))
            finish()
        }
    }
}