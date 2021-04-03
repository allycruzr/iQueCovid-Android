package pt.ulusofona.deisi.a2020.cm.g25

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dashboard.*

class TestListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_list)
    }

    override fun onStart() {
        super.onStart()
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            startActivity(Intent(this, TestFormActivity::class.java))
            finish()
        }
    }
}