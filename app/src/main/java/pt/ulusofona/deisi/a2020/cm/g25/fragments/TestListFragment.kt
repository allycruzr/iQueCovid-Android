package pt.ulusofona.deisi.a2020.cm.g25.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_contacts.*
import pt.ulusofona.deisi.a2020.cm.g25.R

class TestListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        return inflater.inflate(R.layout.fragment_vaccination, container, false)
    }

    override fun onStart() {
        super.onStart()

    }

}