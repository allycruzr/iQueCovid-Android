package pt.ulusofona.deisi.a2020.cm.g25.view.activities.testform

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_test_form.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.TestResult
import pt.ulusofona.deisi.a2020.cm.g25.model.classes.Test
import pt.ulusofona.deisi.a2020.cm.g25.model.static_db.TestList
import pt.ulusofona.deisi.a2020.cm.g25.view.utils.Constants
import pt.ulusofona.deisi.a2020.cm.g25.view.utils.Constants.Companion.REQUEST_IMAGE_CAPTURE
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class TestFormActivity : AppCompatActivity() {

    var bitmap: Bitmap? = null
    var datePicked: Boolean = false

    var formatDate = SimpleDateFormat("dd MMM yyyy", Locale.UK)

    var photo: Bitmap? = null

    val storage = AppDatabase.getInstance(this).appDao()

    // Data variable for saving form when submitting
    /*var data = mutableMapOf<String, Any>()
    var onValidationFunction = ::toastValid*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_form)

        val actionBar: ActionBar? = supportActionBar                                                  // Botão Retroceder na TitleBar da activity
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()



        btn_date_picker.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate:Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR,i)
                selectDate.set(Calendar.MONTH,i2)
                selectDate.set(Calendar.DAY_OF_MONTH,i3)
                val date = formatDate.format(selectDate.time)
                Toast.makeText(this, "Selected date: "+date, Toast.LENGTH_SHORT).show()
                btn_date_picker.text=date
            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicked = true
            datePicker.show()
        }

        form_take_photo_button.setOnClickListener{
            onRequestedPermissions(
                this.baseContext!!, arrayOf(
                    Manifest.permission.CAMERA
                )
            )
        }

        form_submit_button.setOnClickListener{
            if (form_local_text_field.text.length != 0 && radioGroup_result.checkedRadioButtonId != -1 && datePicked){
                var radioButton: RadioButton
                val intSelectButton: Int = radioGroup_result!!.checkedRadioButtonId
                radioButton = findViewById(intSelectButton)

                val date: String = btn_date_picker.text.toString()
                val local: String = form_local_text_field.text.toString()
                val result: String = radioButton.text.toString()
                var file: String = ""
//                if (form_foto_text_field.text != null) {
//                    file = form_foto_text_field.text.toString()
//                }

                val teste: Test = Test(date, local, result, file)
                val testResult = TestResult(date, local, result,convertFromBitMap(this.photo))

                TestList.addTest(teste)
                insertTestResult(testResult)

                val toast = Toast.makeText(this, "Teste Submetido com Sucesso!", Toast.LENGTH_SHORT)
                toast.show()
                this.finish()
            } else {
                if(!datePicked){
                    form_date_error.text = "* Introduza a Data"
                    form_date_error.visibility = View.VISIBLE
                } else {
                    form_date_error.visibility = View.INVISIBLE
                }
                if(radioGroup_result.checkedRadioButtonId == -1) {
                    form_result_text_error.text = "* Seleccione 1 dos Itens"
                    form_result_text_error.visibility = View.VISIBLE
                } else {
                    form_result_text_error.visibility = View.INVISIBLE
                }
                if (form_local_text_field.text.length == 0){
                    form_local_text_error.text = "* Preenchimento Obrigatório"
                    form_local_text_error.visibility = View.VISIBLE
                } else {
                    form_local_text_error.visibility = View.INVISIBLE
                }
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

    fun openDatePicker(view: View) {

    }

//    fun requestCameraPermission() {
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), )
//    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {

                bitmap = data?.extras?.get("data") as Bitmap
                setTestImage(bitmap!!)

            }
        }
    }

    fun setTestImage(bitmap: Bitmap) {
        photo = bitmap
    }

    fun onRequestedPermissions(context: Context, permissions: Array<String>) {
        var permissionsGiven = 0
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(
                    context,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(permissions, Constants.REQUEST_CODE)
            } else {
                permissionsGiven++
            }
        }
        if (permissionsGiven == permissions.size) {
            takePhoto()
        }
    }

    fun convertFromBitMap(bitmap: Bitmap?):   ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
        return  outputStream.toByteArray()
    }

    fun insertTestResult(testResult: TestResult) {
        CoroutineScope(Dispatchers.IO).launch {
            storage.insertTest(testResult)
        }
    }
}