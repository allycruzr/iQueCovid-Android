package pt.ulusofona.deisi.a2020.cm.g25.ui.fragments.testlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
//import kotlinx.android.synthetic.main.activity_test_list.test_recycler_view
import kotlinx.android.synthetic.main.fragment_test_list.*
import pt.ulusofona.deisi.a2020.cm.g25.ui.activities.MainActivity
import pt.ulusofona.deisi.a2020.cm.g25.R
import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.TestResult
import pt.ulusofona.deisi.a2020.cm.g25.ui.adapters.TestAdapter
import pt.ulusofona.deisi.a2020.cm.g25.data.static_db.TestList
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.TestListInterface
import pt.ulusofona.deisi.a2020.cm.g25.ui.activities.testform.TestFormActivity


class TestListFragment : Fragment(), TestListInterface {

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

}