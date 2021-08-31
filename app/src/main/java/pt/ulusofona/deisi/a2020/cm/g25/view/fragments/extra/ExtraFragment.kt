package pt.ulusofona.deisi.a2020.cm.g25.view.fragments.extra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.data_extra.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.model.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.viewmodels.extra.ExtraViewModel

class ExtraFragment : Fragment() {

    private lateinit var viewModel: ExtraViewModel
    val dataSource = DataSource.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExtraViewModel::class.java)
        return inflater.inflate(R.layout.fragment_extra, container, false)
    }

    override fun onStart() {
        super.onStart()
        loadSymptonsData()
    }

    fun loadSymptonsData() {
        extra_dry_cogh_value.text = dataSource.getCoughPercentage().toString() + "%"
        extra_fever_value.text = dataSource.getFeverPercentage().toString() + "%"
        extra_headache_value.text = dataSource.getHeadAchePercentage().toString() + "%"
        extra_shortBreath_value.text = dataSource.getShortBreathPercentage().toString() + "%"
        extra_muscle_ache_value.text = dataSource.getMuscleAchesPercentage().toString() + "%"
        extra_general_weakness_value.text = dataSource.getGeneralWeaknessPercentage().toString() + "%"
    }
}