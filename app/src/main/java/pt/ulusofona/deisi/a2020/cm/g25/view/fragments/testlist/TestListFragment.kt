package pt.ulusofona.deisi.a2020.cm.g25.view.fragments.testlist

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.location.LocationResult
//import kotlinx.android.synthetic.main.activity_test_list.test_recycler_view
import kotlinx.android.synthetic.main.fragment_test_list.*
import pt.ulusofona.deisi.a2020.cm.g25.view.activities.MainActivity
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.TestResult
import pt.ulusofona.deisi.a2020.cm.g25.view.adapters.TestAdapter
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.TestListInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g25.view.activities.testform.TestFormActivity
import java.util.*
import kotlin.collections.ArrayList


class TestListFragment : Fragment(), TestListInterface, OnLocationChangedListener {

    private lateinit var viewModel: TestListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this, fragment_fab)
        viewModel = ViewModelProviders.of(this).get(TestListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_test_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        //val testes = TestList
        viewModel.registerListener(this)
        viewModel.getTestsList()


        @OnClick(R.id.fragment_fab)
        fun setOnClickListener(view: View) {
            Log.i("TestListFragment", "$activity")
            startActivity(Intent(MainActivity(), TestFormActivity::class.java))
        }

        fragment_fab.setOnClickListener { view ->
            Log.i("TestListFragment", "$activity")
            startActivity(Intent(activity, TestFormActivity::class.java))
        }
    }

    override fun getTestsListCompleted(list: ArrayList<TestResult>) {
        test_recycler_view.adapter = TestAdapter(list)
    }

    override fun onLocationChangedListener(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        val newLocation = locationResult.lastLocation
        val gcd = Geocoder(context, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(newLocation.latitude, newLocation.longitude, 1)

        //location != newLocation
            locationText.text = addresses[0].toString()
    }
}