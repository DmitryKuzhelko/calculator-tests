package com.example.dahaka.mycaculator.activity

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import com.example.dahaka.mycaculator.R
import com.example.dahaka.mycaculator.adapter.ViewModelAdapter
import com.example.dahaka.mycaculator.model.CalculatorViewModel
import com.example.dahaka.mycaculator.util.daggerComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewModelAdapter.ItemClickListener {

    @Inject
    lateinit var viewModel: CalculatorViewModel

    private val adapter: ViewModelAdapter by lazy {
        ViewModelAdapter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        daggerComponent.inject(this)
        initRV()
        viewModel.historyList().observe(this, Observer { history ->
            adapter.refreshHistoryList(history)
        })
        viewModel.showDisplay().observe(this, Observer { text ->
            display.text = text
        })
        history.setOnClickListener {
            showOrHideHistory()
        }
        clear_db.setOnClickListener {
            viewModel.clearDb()
        }
        erase.setOnClickListener {
            viewModel.removeLastSymbol()
        }
        erase.setOnLongClickListener {
            viewModel.clearDisplay()
            true
        }
    }

    private fun showOrHideHistory() {
        if (calculator.visibility == VISIBLE) {
            recycler.visibility = VISIBLE
            calculator.visibility = GONE
            clear_db.visibility = VISIBLE
            history.text = getString(R.string.back)
        } else {
            recycler.visibility = GONE
            calculator.visibility = VISIBLE
            clear_db.visibility = GONE
            history.text = getString(R.string.history)
        }
    }

    private fun initRV() {
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }

    fun numberClick(v: View?) {
        val button = v as Button
        viewModel.setNumber(button.text.toString())
    }

    fun buttonClick(v: View) {
        val button = v as Button
        viewModel.setOperator(button.text.toString())
    }

    fun equalsClick(v: View) {
        val button = v as Button
        viewModel.setResult(button.text.toString())
    }

    override fun onItemClicked(viewHolder: ViewModelAdapter.ViewHolder) {
        viewModel.historyList().observe(this, Observer { history ->
            if (history?.isNotEmpty()!!) {
                viewModel.setHistoryValue(history[viewHolder.adapterPosition].expression)
            }
        })
        showOrHideHistory()
    }
}
