package pt.ulusofona.deisi.a2020.cm.g25.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import pt.ulusofona.deisi.a2020.cm.g25.MainActivity
import pt.ulusofona.deisi.a2020.cm.g25.R

class SplashScreenActivity : AppCompatActivity() {

    private var TIME_OUT: Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_splashscreen)
        loadSplashScreen()
    }

    private fun loadSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_OUT)
    }
}