package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.extra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pt.ulusofona.deisi.a2020.cm.g25.R

class ExtraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        return inflater.inflate(R.layout.fragment_extra, container, false)
    }

    override fun onStart() {
        super.onStart()
    }
}