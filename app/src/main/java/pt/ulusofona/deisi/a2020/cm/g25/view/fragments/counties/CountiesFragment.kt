package pt.ulusofona.deisi.a2020.cm.g25.view.fragments.counties

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_counties.*
import kotlinx.android.synthetic.main.fragment_test_list.test_recycler_view
import kotlinx.android.synthetic.main.header_filter_counties.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.CountiesInterface
import pt.ulusofona.deisi.a2020.cm.g25.view.adapters.CountiesAdapter
import pt.ulusofona.deisi.a2020.cm.g25.view.adapters.CountiesHeaderAdapter
import pt.ulusofona.deisi.a2020.cm.g25.viewmodel.CountiesViewModel

class CountiesFragment : Fragment(), CountiesInterface {
    private lateinit var adapter: CountiesAdapter
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

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        viewModel.searchCounties()

        adapter = CountiesAdapter(arrayListOf())
        view?.findViewById<RecyclerView>(R.id.test_recycler_view)?.adapter =
            ConcatAdapter(CountiesHeaderAdapter(viewModel), adapter)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCountySearched(counties: ArrayList<County>) {
        adapter.dataSet = counties
        adapter.notifyDataSetChanged()
    }
}