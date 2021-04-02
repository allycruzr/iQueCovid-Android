package pt.ulusofona.deisi.a2020.cm.g25

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
    }

    override fun onStart() {
        super.onStart()

        setTitle(getResources().getString(R.string.activity_contacts_name))

        button_contacts_tel.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:808242424")
            startActivity(intent)
        }

        button_contacts_email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:atendimento@sns24.gov.pt")
            startActivity(intent)
        }

        button_contacts_website.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.sns24.gov.pt/")
            startActivity(intent)
        }
    }
}