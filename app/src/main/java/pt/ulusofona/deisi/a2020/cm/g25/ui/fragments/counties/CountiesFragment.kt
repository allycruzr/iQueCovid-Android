package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.counties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_counties.*
import kotlinx.android.synthetic.main.fragment_test_list.*
import kotlinx.android.synthetic.main.fragment_test_list.test_recycler_view
import pt.ulusofona.deisi.a2020.cm.g25.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confSearchName()
    }

    private fun confSearchName() {
        view?.findViewById<EditText>(R.id.mSearchName)?.addTextChangedListener {
            val text = it!!.toString()
            viewModel.searchCounties(text)

        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        viewModel.searchCounties()
    }

    override fun onCountySearched(counties: ArrayList<County>) {
        test_recycler_view.adapter = CountiesAdapter(counties)
    }
}