package pt.ulusofona.deisi.a2020.cm.g25.view.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.data_dashboard.*
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.model.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.viewmodel.dashboard.DashboardViewModel
import java.lang.Math.abs

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel

    val dataSource = DataSource.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onStart() {
        super.onStart()
        loadDashboardData()
    }

    fun loadDashboardData(){
        dashboard_data_cases.text = "+ " + abs(dataSource.confirmedToday()).toString()
        dashboard_data_recovered.text = "+ " + abs(dataSource.recoveredToday()).toString()
        dashboard_data_deaths.text = "+ " + abs(dataSource.deathsToday()).toString()

        /** Active Cases **/
        dashboard_north_cases.text = "+ " + dataSource.confirmedNorthToday().toString()
        dashboard_lisbon_cases.text = "+ " + dataSource.confirmedLVTToday().toString()
        dashboard_center_cases.text = "+ " + dataSource.confirmedCenterToday().toString()
        dashboard_alentejo_cases.text = "+ " + dataSource.confirmedAlentejoToday().toString()
        dashboard_faro_cases.text = "+ " + dataSource.confirmedAlgarveToday().toString()
        dashboard_azores_cases.text = "+ " + dataSource.confirmedAzoresToday().toString()
        dashboard_madeira_cases.text = "+ " + dataSource.confirmedMadeiraToday().toString()

        /** Recovered Cases **/
        dashboard_north_recovered.text = "+ " + dataSource.recoveredNorthToday().toString()
        dashboard_lisbon_recovered.text = "+ " + dataSource.recoveredLVTToday().toString()
        dashboard_center_recovered.text = "+ " + dataSource.recoveredCenterToday().toString()
        dashboard_alentejo_recovered.text = "+ " + dataSource.recoveredAlentejoToday().toString()
        dashboard_faro_recovered.text = "+ " + dataSource.recoveredAlgarveToday().toString()
        dashboard_azores_recovered.text = "+ " + dataSource.recoveredAzoresToday().toString()
        dashboard_madeira_recovered.text = "+ " + dataSource.recoveredMadeiraToday().toString()

        /** Deaths **/
        dashboard_north_deaths.text = "+ " + dataSource.deathsNorthToday().toString()
        dashboard_lisbon_deaths.text = "+ " + dataSource.deathsLVTToday().toString()
        dashboard_center_deaths.text = "+ " + dataSource.deathsCenterToday().toString()
        dashboard_alentejo_deaths.text = "+ " + dataSource.deathsAlentejoToday().toString()
        dashboard_faro_deaths.text = "+ " + dataSource.deathsAlgarveToday().toString()
        dashboard_azores_deaths.text = "+ " + dataSource.deathsAzoresToday().toString()
        dashboard_madeira_deaths.text = "+ " + dataSource.deathsMadeiraToday().toString()

        title_data_updated_at.text = getString(R.string.title_data_updated_at) + " " + dataSource.dateOfData()
    }
}