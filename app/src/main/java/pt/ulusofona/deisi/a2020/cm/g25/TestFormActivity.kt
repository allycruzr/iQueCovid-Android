package pt.ulusofona.deisi.a2020.cm.g25

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contacts.*
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi

class TestFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_form)

        /*if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColorTo(R.color.design_default_color_primary)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun Window.setStatusBarColorTo(color: Int){
            this.ClearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }*/
    }

    override fun onStart() {
        super.onStart()

        setTitle(getResources().getString(R.string.activity_insert_test_form_name))

    }
}