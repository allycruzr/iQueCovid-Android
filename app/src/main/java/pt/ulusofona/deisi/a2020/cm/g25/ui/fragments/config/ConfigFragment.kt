package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.settings_fragment.*
import pt.ulusofona.deisi.a2020.cm.g25.R

class ConfigFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()



        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            btn_theme_switch.text = "Dark Mode ON"
        } else {
            btn_theme_switch.text = "Dark Mode OFF"
        }


        btn_theme_switch.setOnClickListener {
            if (btn_theme_switch.isChecked) {
                btn_theme_switch.isChecked = false
                btn_theme_switch.text = "Dark Mode ON"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                btn_theme_switch.isChecked = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                btn_theme_switch.text = "Dark Mode OFF"
            }

        }
    }
}