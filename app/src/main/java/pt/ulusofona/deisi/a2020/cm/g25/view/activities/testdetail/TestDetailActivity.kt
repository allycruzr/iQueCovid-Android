package pt.ulusofona.deisi.a2020.cm.g25.view.activities.testdetail

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_detail.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.model.local.datasource.DataSource

class TestDetailActivity : AppCompatActivity() {

    val dataSource = DataSource.getInstance()

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
        val file = intent.getByteArrayExtra("FILE")

        var options = BitmapFactory.Options()

        var bitmap = BitmapFactory.decodeByteArray(file, 0, file!!.size, options)

        detail_text_date.text = date
        detail_text_local.text = local
        detail_text_result.text = result
        if (bitmap == null) {
            pic_test.setImageResource(R.drawable.no_image)
            pic_test.layoutParams.height = 200 //LinearLayoutCompat.LayoutParams.WRAP_CONTENT        // Alterar para DP!!!
            pic_test.layoutParams.width = 200 //LinearLayoutCompat.LayoutParams.WRAP_CONTENT         // Alterar para DP!!!
        } else {
            pic_test.setImageBitmap(bitmap)
//            var fullscreenPic = findViewById<ImageView>(R.id.fullscreen_pic_test)
//            fullscreenPic.setImageBitmap(bitmap)
//            detail_file_name.text = file
//            detail_file_name.visibility = View.VISIBLE
//            pic_test.setOnClickListener{
//                val fullScreenIntent = Intent(this, ImageFullscreenActivity::class.java)
//                intent.apply {
//                    putExtra("FILE", file)
//                }
//                startActivity(fullScreenIntent)
//            }
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