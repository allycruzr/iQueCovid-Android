package pt.ulusofona.deisi.a2020.cm.g25.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.databinding.ActivityContactsBinding
import pt.ulusofona.deisi.a2020.cm.g25.databinding.FragmentTestDetailBinding
import java.io.File

class TestDetailFragment : Fragment(){

    lateinit var binding: FragmentTestDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding =  FragmentTestDetailBinding.inflate(inflater)

        // TODO Passar valor depois
        val date = "DATE"
        val local = "LOCAL"
        val result = "RESULT"
        val file = "FILE"

        binding.detailTextDate.text = date
        binding.detailTextLocal.text = local
        binding.detailTextResult.text = result

        if (file=="N/A") {
            with (binding.picTest){
                setImageResource(R.drawable.no_image)
                layoutParams.height = 200 //LinearLayoutCompat.LayoutParams.WRAP_CONTENT        // Alterar para DP!!!
                layoutParams.width = 200 //LinearLayoutCompat.LayoutParams.WRAP_CONTENT         // Alterar para DP!!!
            }

        } else {
            binding.detailFileName.apply {
                text = file
                visibility = View.VISIBLE
            }
            binding.picTest.setOnClickListener{
               // TODO Após nav implementada navegar até o fragment full screen
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}