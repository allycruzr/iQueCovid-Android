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
import android.content.Context

import android.content.SharedPreferences




class SettingsFragment() : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("iquecovid", Context.MODE_PRIVATE)

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onStart() {
        super.onStart()

        val darkMode = sharedPreferences.getBoolean("DARK_MODE", true)
        confDarkMode(darkMode)
        btn_theme_switch.isChecked = darkMode
        btn_theme_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            confDarkMode(isChecked)
        }

        val shake = sharedPreferences.getBoolean("SHAKE", true)
        confShake(shake)
        btn_shake_switch.isChecked = shake
        btn_shake_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            confShake(isChecked)
        }
    }

    fun confDarkMode(isChecked: Boolean){
        if (isChecked)  {
            theme_change_text.text = requireContext().resources.getString(R.string.dark_mode_on)
        } else if (!isChecked) {
            theme_change_text.text = requireContext().resources.getString(R.string.dark_mode_off)
        }

        val editor = sharedPreferences.edit()
        editor.putBoolean("DARK_MODE", isChecked)
        editor.apply()
    }

    fun confShake(isChecked: Boolean){
        if (isChecked)  {
            shake_text.text = requireContext().resources.getString(R.string.shake_on)
        } else if (!isChecked) {
            shake_text.text = requireContext().resources.getString(R.string.shake_off)
        }

        val editor = sharedPreferences.edit()
        editor.putBoolean("SHAKE", isChecked)
        editor.apply()
    }
}