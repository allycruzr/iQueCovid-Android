package pt.ulusofona.deisi.a2020.cm.g25.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_test_form.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import java.text.SimpleDateFormat
import java.util.*

class TestFormActivity : AppCompatActivity() {

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
            datePicker.show()
        }
    }

    override fun onStart() {
        super.onStart()

        form_submit_button.setOnClickListener{

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

    /*private fun onSubmit(){
        updateData()
        if(isValid())
            onValidationFunction()
    }

    private fun isValid(): Boolean {
        var valid = true
        for (field in fields)
            if (! field.isValid(container, data))
                valid = false
        return valid
    }

    private fun updateData()  {
        data.clear()
        for (field in fields)
            data[field.name] = field.getInput(container)
    }

    private fun toastValid(){
        Toast.makeText(activity,"Valid",Toast.LENGTH_SHORT).show()
    }*/
}