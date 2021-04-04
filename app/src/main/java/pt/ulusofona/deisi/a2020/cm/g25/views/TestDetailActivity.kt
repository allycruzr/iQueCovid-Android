package pt.ulusofona.deisi.a2020.cm.g25.views

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_detail.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import java.io.File
import java.io.FileOutputStream


@Suppress("DEPRECATION")
class TestDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_detail)

        val actionBar: ActionBar? = supportActionBar                                                  // Botão Retroceder na TitleBar da activity
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        val bm = BitmapFactory.decodeResource(resources, R.drawable.teste_covid)
        val extStorageDirectory = getExternalStorageDirectory().toString()


        ///******************* Não está funcional!!!
        val file1 = File(extStorageDirectory, "teste_covid.png")
        val outStream = FileOutputStream(file1)
        bm.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        outStream.flush()
        outStream.close()
        ///******************* Não está funcional!!!


        val date = intent.getStringExtra("DATE")
        val local = intent.getStringExtra("LOCAL")
        val result = intent.getStringExtra("RESULT")
        val file = intent.getStringExtra("FILE")

        detail_text_date.text = date
        detail_text_local.text = local
        detail_text_result.text = result
        //detail_text_file.text = date

        button_open_picture.setOnClickListener{
            ///******************* Não está funcional!!!
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("file://" + extStorageDirectory + "teste_covid.png")
            startActivity(intent)
            ///******************* Não está funcional!!!
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