package pt.ulusofona.deisi.a2020.cm.g25.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_test_form.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.Test
import pt.ulusofona.deisi.a2020.cm.g25.database.TestList
import java.text.SimpleDateFormat
import java.util.*

class TestFormActivity : AppCompatActivity() {

    var datePicked: Boolean = false

    var formatDate = SimpleDateFormat("dd MMM YYYY", Locale.UK)

    // Data variable for saving form when submitting
    /*var data = mutableMapOf<String, Any>()
    var onValidationFunction = ::toastValid*/

    @RequiresApi(Build.VERSION_CODES.N)
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

        form_submit_button.setOnClickListener{
            if (form_local_text_field.text.length != 0 && radioGroup_result.checkedRadioButtonId != -1 && datePicked){
                var radioButton: RadioButton
                val intSelectButton: Int = radioGroup_result!!.checkedRadioButtonId
                radioButton = findViewById(intSelectButton)

                val date: String = btn_date_picker.text.toString()
                val local: String = form_local_text_field.text.toString()
                val result: String = radioButton.text.toString()
                var file: String = ""
                if (form_foto_text_field.text != null) {
                    file = form_foto_text_field.text.toString()
                }

                val teste: Test = Test(date, local, result, file)
                TestList.addTest(teste)
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
}