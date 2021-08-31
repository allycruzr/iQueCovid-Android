package pt.ulusofona.deisi.a2020.cm.g25.view.fragments.settings

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.*
import pt.ulusofona.deisi.a2020.cm.g25.R

class SettingsFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onStart() {
        super.onStart()

        val mode = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        when (mode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                theme_mode_text.text = "Dark Mode ON"
                theme_mode_text.setTextColor(Color.WHITE)
                btn_theme_switch.isChecked = true
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                theme_mode_text.text = "Dark Mode OFF"
                theme_mode_text.setTextColor(Color.BLACK)
                btn_theme_switch.isChecked = false
            }
        }

        btn_theme_switch.setOnCheckedChangeListener { buttonview, isChecked ->
            if (isChecked)  {
                theme_mode_text.setTextColor(Color.WHITE)
                theme_mode_text.text = "Dark Mode ON"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else if (!isChecked) {
                theme_mode_text.setTextColor(Color.BLACK)
                theme_mode_text.text = "Dark Mode OFF"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}