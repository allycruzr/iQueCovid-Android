package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import pt.ulusofona.deisi.a2020.cm.g25.R

class ContactsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)


        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onStart() {
        super.onStart()

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