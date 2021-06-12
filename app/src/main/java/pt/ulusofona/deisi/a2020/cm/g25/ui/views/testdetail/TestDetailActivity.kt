package pt.ulusofona.deisi.a2020.cm.g25.ui.views.testdetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_detail.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.ui.views.imagefullscreen.ImageFullscreenActivity

class TestDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_detail)

        val actionBar: ActionBar? = supportActionBar                                                  // Botão Retroceder na TitleBar da activity
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        val date = intent.getStringExtra("DATE")
        val local = intent.getStringExtra("LOCAL")
        val result = intent.getStringExtra("RESULT")
        val file = intent.getStringExtra("FILE")

        detail_text_date.text = date
        detail_text_local.text = local
        detail_text_result.text = result
        if (file=="N/A") {
            pic_test.setImageResource(R.drawable.no_image)
            pic_test.layoutParams.height = 200 //LinearLayoutCompat.LayoutParams.WRAP_CONTENT        // Alterar para DP!!!
            pic_test.layoutParams.width = 200 //LinearLayoutCompat.LayoutParams.WRAP_CONTENT         // Alterar para DP!!!
        } else {
            detail_file_name.text = file
            detail_file_name.visibility = View.VISIBLE
            pic_test.setOnClickListener{
                val fullScreenIntent = Intent(this, ImageFullscreenActivity::class.java)
                startActivity(fullScreenIntent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {                                     // Define o comportamento do botão Retroceder do TitleBar
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}