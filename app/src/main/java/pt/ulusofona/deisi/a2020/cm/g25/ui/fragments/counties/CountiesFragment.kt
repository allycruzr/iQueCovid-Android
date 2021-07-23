package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.counties

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_test_list.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.data.static_db.TestList
import pt.ulusofona.deisi.a2020.cm.g25.ui.activities.MainActivity
import pt.ulusofona.deisi.a2020.cm.g25.ui.activities.testform.TestFormActivity
import pt.ulusofona.deisi.a2020.cm.g25.ui.adapters.CountiesAdapter
import pt.ulusofona.deisi.a2020.cm.g25.ui.adapters.TestAdapter

class CountiesFragment : Fragment() {

    val dataSource = DataSource.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this, fragment_fab)
        return inflater.inflate(R.layout.fragment_counties, container, false)
    }

    override fun onStart() {
        super.onStart()

        val counties: ArrayList<County> = dataSource.getAllCounties()

        test_recycler_view.adapter = CountiesAdapter(counties)
    }

}