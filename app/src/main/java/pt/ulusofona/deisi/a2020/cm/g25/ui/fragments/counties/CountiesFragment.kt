package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.counties

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_test_list.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.CountiesInterface
import pt.ulusofona.deisi.a2020.cm.g25.ui.adapters.CountiesAdapter
import pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels.CountiesViewModel

class CountiesFragment : Fragment(), CountiesInterface { // TODO: Criar a pesquisa dos counties
    private lateinit var viewModel: CountiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountiesViewModel::class.java)
        ButterKnife.bind(this, fragment_fab)
        return inflater.inflate(R.layout.fragment_counties, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        viewModel.getAllCounties()
    }

    override fun initCounties(allCounties: ArrayList<County>) {
        test_recycler_view.adapter = CountiesAdapter(allCounties)
    }
}