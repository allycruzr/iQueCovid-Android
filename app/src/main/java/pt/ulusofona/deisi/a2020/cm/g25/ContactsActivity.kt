package pt.ulusofona.deisi.a2020.cm.g25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        getActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}