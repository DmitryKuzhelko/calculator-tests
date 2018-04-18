package dmitry.kuzhelko.calculator.ui.history.view

import dmitry.kuzhelko.calculator.storage.entity.Expression

interface HistoryView {

    fun showAllExamples(examples: List<Expression>)

    fun loadAllExamples()

    fun initRecyclerView()

    fun showExampleById(id: Int?)

    fun setAdapterClickListener()

    fun showError(error: String)
}