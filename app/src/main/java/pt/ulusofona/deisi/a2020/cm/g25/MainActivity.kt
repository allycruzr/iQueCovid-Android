package pt.ulusofona.deisi.a2020.cm.g25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pt.ulusofona.deisi.a2020.cm.g25.navigation.NavigationManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.goToDashBoardFragment(supportFragmentManager)
    }
}