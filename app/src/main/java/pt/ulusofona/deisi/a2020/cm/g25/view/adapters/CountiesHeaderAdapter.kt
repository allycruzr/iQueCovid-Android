package pt.ulusofona.deisi.a2020.cm.g25.view.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.header_filter_counties.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.viewmodel.CountiesViewModel
import kotlin.math.min

class CountiesHeaderAdapter(val viewModel: CountiesViewModel) : RecyclerView.Adapter<CountiesHeaderAdapter.ViewHolder>() {
    class ViewHolder(val view: View, val viewModel: CountiesViewModel) : RecyclerView.ViewHolder(view) {
        private val btnCleanFilter = view.findViewById<Button>(R.id.btn_clean_filters)
        private val btnFilter = view.findViewById<Button>(R.id.btn_filter)
        private val minimum = view.findViewById<TextView>(R.id.minimum)
        private val maximum = view.findViewById<TextView>(R.id.maximum)
        private val riskSelector = view.findViewById<Spinner>(R.id.risk_selector)
        private val mSearchName = view.findViewById<TextView>(R.id.mSearchName)

        private fun confCleanFilter() {
            btnCleanFilter?.setOnClickListener {
                minimum.text = ""
                maximum.text = ""
                riskSelector.setSelection(0)
                mSearchName.text = ""
            }
        }

        private fun confSearchName() {
            mSearchName.addTextChangedListener {
                Log.d("xxxxx", it!!.toString())
                startSearch()
            }
        }

        private fun confSearchCounties() {
            btnFilter.setOnClickListener {
                startSearch()
            }
        }

        private fun startSearch() {
            val selectedItemPosition = riskSelector.selectedItemPosition
            val risk = view.context.resources.getTextArray(R.array.risk)[selectedItemPosition].toString()
            viewModel.searchCounties(
                mSearchName.text.toString(),
                if (selectedItemPosition == 0) null else risk,
                minimum.text.toString().toIntOrNull(),
                maximum.text.toString().toIntOrNull()
            )
        }

        fun bind() {
            confCleanFilter()
            confSearchName()
            confSearchCounties()
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_filter_counties, parent, false)

        return ViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}