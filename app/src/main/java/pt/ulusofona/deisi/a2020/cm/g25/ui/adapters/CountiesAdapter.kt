package pt.ulusofona.deisi.a2020.cm.g25.ui.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.domain.classes.Test
import pt.ulusofona.deisi.a2020.cm.g25.ui.activities.testdetail.TestDetailActivity

class CountiesAdapter(private val dataSet: ArrayList<County>):
    RecyclerView.Adapter<CountiesAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val countieNameView: TextView
            val districtTextView: TextView
            val lastUpdateTextView: TextView
            val rateTextView: TextView
            val rateRiskTextView: TextView
            val context: Context

            init {
                // Define click listener for the ViewHolder's View.
                countieNameView = view.findViewById(R.id.text_county_name)
                districtTextView = view.findViewById(R.id.text_district)
                lastUpdateTextView = view.findViewById(R.id.text_last_update)
                rateTextView = view.findViewById(R.id.text_rate)
                rateRiskTextView = view.findViewById(R.id.text_rate_risk)
                context = view.context
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_county, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            val context: Context = viewHolder.context

            val county = getItem(position)!!
            var countyName = county.county!!
            var district = county.district!!
            var lastUpdate = county.dateOfData!!
            var rate = county.rate!!
            var risk = county.risk!!

            viewHolder.countieNameView.text = county.county
            viewHolder.districtTextView.text = county.district
            viewHolder.lastUpdateTextView.text = county.dateOfData
            viewHolder.rateTextView.text = county.rate.toString()
            viewHolder.rateRiskTextView.text = county.risk

            if (county.risk.equals("Baixo a Moderado")) {
                viewHolder.rateRiskTextView.setTextColor(Color.GREEN)
            } else if(county.risk.equals("Moderado")) {
                viewHolder.rateRiskTextView.setTextColor(Color.rgb(246,190,0))
            } else if(county.risk.equals("Elevado")) {
                viewHolder.rateRiskTextView.setTextColor(Color.rgb(255,165,0))
            } else if(county.risk.equals("Muito Elevado")) {
                viewHolder.rateRiskTextView.setTextColor(Color.rgb(255,103,0))
            } else if(county.risk.equals("Extremamente Elevado")) {
                viewHolder.rateRiskTextView.setTextColor(Color.RED)
            }
        }

        private fun getItem(position: Int): County {
            return dataSet[position]
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

        override fun getItemId(position: Int): Long {
            return super.getItemId(position)
        }
}