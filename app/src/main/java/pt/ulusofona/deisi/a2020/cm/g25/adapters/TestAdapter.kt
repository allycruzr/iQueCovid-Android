package pt.ulusofona.deisi.a2020.cm.g25.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.Test
import pt.ulusofona.deisi.a2020.cm.g25.views.TestDetailActivity

class TestAdapter(private val dataSet: ArrayList<Test>) :
        RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val resultImageView: ImageView
        val dateTextView: TextView
        val localTextView: TextView
        val context: Context

        init {
            // Define click listener for the ViewHolder's View.
            resultImageView = view.findViewById(R.id.ic_result)
            dateTextView = view.findViewById(R.id.text_date)
            localTextView = view.findViewById(R.id.text_local)
            context = view.context
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_test, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val context: Context = viewHolder.context

        val teste = getItem(position)!!

        var DATE = teste.date
        var LOCAL = teste.local
        var RESULT = teste.resultado
        var FILE = teste.ficheiro

        viewHolder.dateTextView.text = teste.date
        viewHolder.localTextView.text = teste.local
        if (teste.resultado == "Positivo") {
            viewHolder.resultImageView.setImageResource(R.drawable.ic_test_negative)
        } else if (teste.resultado == "Negativo") {
            viewHolder.resultImageView.setImageResource(R.drawable.ic_test_positive)
        } else {
            viewHolder.resultImageView.setImageResource(R.drawable.ic_test_unknown)
        }

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, TestDetailActivity::class.java)
            intent.apply{
                putExtra("DATE", DATE)
                putExtra("LOCAL", LOCAL)
                putExtra("RESULT", RESULT)
                putExtra("FILE", FILE)}
            context.startActivity(intent)
        }
    }

    private fun getItem(position: Int): Test {
        return dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

}