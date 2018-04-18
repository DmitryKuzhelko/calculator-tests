package dmitry.kuzhelko.calculator.ui.history.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import dmitry.kuzhelko.calculator.App
import dmitry.kuzhelko.calculator.R
import dmitry.kuzhelko.calculator.storage.entity.Expression
import dmitry.kuzhelko.calculator.ui.history.HistoryAdapter
import dmitry.kuzhelko.calculator.ui.history.presenter.HistoryPresenterImpl
import kotlinx.android.synthetic.main.activity_history.*
import javax.inject.Inject

class HistoryActivity : AppCompatActivity(), HistoryView {

    @Inject
    lateinit var presenter: HistoryPresenterImpl

    @Inject
    lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        App.appComponent.inject(this)
        presenter.onAttach(this)

        initRecyclerView()
        setAdapterClickListener()
        loadAllExamples()
    }

    override fun setAdapterClickListener() {
        historyAdapter.setClickListener(object : HistoryAdapter.ClickListener {
            override fun onClick(example: Expression) {
                showExampleById(example.id)
            }
        })
    }

    override fun loadAllExamples() {
        presenter.loadAllExamples()
    }

    override fun showAllExamples(examples: List<Expression>) {
        historyAdapter.updateAdapter(examples)
    }

    override fun showExampleById(id: Int?) {
        presenter.openMainActivity(id)
    }


    override fun initRecyclerView() {
        rv.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = historyAdapter
        }
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}