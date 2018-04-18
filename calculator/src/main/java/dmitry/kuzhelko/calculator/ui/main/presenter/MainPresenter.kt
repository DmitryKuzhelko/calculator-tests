package dmitry.kuzhelko.calculator.ui.main.presenter

interface MainPresenter<in MainView> {

    fun onAttach(mvpView: MainView)

    fun onDetach()

    fun saveToDb(example: String)

    fun getById(id: Int?)

    fun showHistory()
}