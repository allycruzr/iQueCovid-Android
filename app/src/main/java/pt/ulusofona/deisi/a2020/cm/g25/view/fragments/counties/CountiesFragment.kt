package pt.ulusofona.deisi.a2020.cm.g25.view.fragments.counties

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.CountiesInterface
import pt.ulusofona.deisi.a2020.cm.g25.view.adapters.CountiesAdapter
import pt.ulusofona.deisi.a2020.cm.g25.view.adapters.CountiesHeaderAdapter
import pt.ulusofona.deisi.a2020.cm.g25.viewmodel.CountiesViewModel
import android.widget.Toast

import kotlin.math.sqrt


class CountiesFragment : Fragment(), CountiesInterface, SensorEventListener {
    private lateinit var adapter: CountiesAdapter
    private lateinit var headerAdapter: CountiesHeaderAdapter
    private lateinit var viewModel: CountiesViewModel
    private lateinit var manager: SensorManager

    private var current = 0.0
    private var last = 0.0
    private var accel = 0.0

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
        manager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI)
        viewModel.registerListener(this)
        viewModel.searchCounties()

        adapter = CountiesAdapter(arrayListOf())
        headerAdapter = CountiesHeaderAdapter(viewModel)
        view?.findViewById<RecyclerView>(R.id.test_recycler_view)?.adapter =
            ConcatAdapter(headerAdapter, adapter)
    }

    override fun onStop() {
        super.onStop()
        manager.unregisterListener(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCountySearched(counties: ArrayList<County>) {
        adapter.dataSet = counties
        adapter.notifyDataSetChanged()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event == null) return

        val x = event.values[0].toDouble()
        val y = event.values[1].toDouble()
        val z = event.values[2].toDouble()
        last = current
        current = sqrt(x * x + y * y + z * z)
        val delta = current - last
        accel = accel * 0.9f + delta // perform low-cut filter


        if (accel > 40) {
            headerAdapter.cleanFilters()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}