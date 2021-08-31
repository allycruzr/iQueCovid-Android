package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.counties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_counties.*
import kotlinx.android.synthetic.main.fragment_counties.view.*
import kotlinx.android.synthetic.main.fragment_test_list.*
import kotlinx.android.synthetic.main.fragment_test_list.test_recycler_view
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.CountiesInterface
import pt.ulusofona.deisi.a2020.cm.g25.ui.adapters.CountiesAdapter
import pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels.CountiesViewModel

class CountiesFragment : Fragment(), CountiesInterface {
    private lateinit var viewModel: CountiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountiesViewModel::class.java)

        return inflater.inflate(R.layout.fragment_counties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confSearchName()
        confCleanFilter()
        confSearchCounties()
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        viewModel.searchCounties()
    }

    private fun confSearchName() {
        view?.findViewById<EditText>(R.id.mSearchName)?.addTextChangedListener {
            startSearch()
        }
    }

    private fun confSearchCounties() {
        view?.findViewById<Button>(R.id.btn_filter)?.setOnClickListener {
            startSearch()
        }
    }

    private fun startSearch() {
        val selectedItemPosition = risk_selector.selectedItemPosition
        val risk = resources.getTextArray(R.array.risk)[selectedItemPosition].toString()
        viewModel.searchCounties(
            mSearchName.text.toString(),
            if(selectedItemPosition == 0) null else risk,
            minimum.text.toString().toIntOrNull(),
            maximum.text.toString().toIntOrNull()
        )
    }

    private fun confCleanFilter() {
        view?.findViewById<Button>(R.id.btn_clean_filters)?.setOnClickListener {
            minimum.setText("")
            maximum.setText("")
            risk_selector.setSelection(0)
            mSearchName.setText("")
        }
    }

    override fun onCountySearched(counties: ArrayList<County>) {
        test_recycler_view.adapter = CountiesAdapter(counties)
    }
}