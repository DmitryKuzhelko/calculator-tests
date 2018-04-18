package dmitry.kuzhelko.calculator.ui.history.presenter


interface HistoryPresenter<in HistoryView> {

    fun loadAllExamples()

    fun openMainActivity(id:Int?)

    fun onAttach(mvpView: HistoryView)

    fun onDetach()
}