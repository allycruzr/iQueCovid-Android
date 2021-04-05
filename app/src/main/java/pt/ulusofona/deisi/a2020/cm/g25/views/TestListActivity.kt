package pt.ulusofona.deisi.a2020.cm.g25.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_test_list.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.adapters.TestAdapter
import pt.ulusofona.deisi.a2020.cm.g25.database.TestList

class TestListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_list)
    }

    override fun onStart() {
        super.onStart()
        val fab: View = findViewById(R.id.fab)

        /*val test1: Test = Test("01-01-2021", "Hospital Santa Maria", "Positivo", "xxx")
        val test2: Test = Test("01-01-2021", "Hospital São José", "Negativo", "xxx")
        val test3: Test = Test("01-01-2021", "Hospital Beatriz Ângelo", "N/A", "xxx")

        val testes: ArrayList<Test> = arrayListOf(test1, test2, test3)
         */
        val testes: TestList = TestList

        test_recycler_view.adapter = TestAdapter(testes.testList)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, TestFormActivity::class.java))
        }
    }
}