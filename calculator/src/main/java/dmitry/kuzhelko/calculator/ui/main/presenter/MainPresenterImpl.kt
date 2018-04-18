package dmitry.kuzhelko.calculator.ui.main.presenter

import dmitry.kuzhelko.calculator.router.Router
import dmitry.kuzhelko.calculator.storage.LocalStorage
import dmitry.kuzhelko.calculator.ui.main.view.MainView
import io.reactivex.Scheduler
import javax.inject.Inject

class MainPresenterImpl
@Inject constructor(private val storage: LocalStorage,
                    private val router: Router,
                    private val workSchedulers: Scheduler,
                    private val resultSchedulers: Scheduler) : MainPresenter<MainView> {

    private var view: MainView? = null

    override fun onAttach(mvpView: MainView) {
        this.view = mvpView
    }

    override fun onDetach() {
        view = null
    }

    override fun saveToDb(example: String) {
        storage.saveExpression(example)
    }

    override fun showHistory() {
        router.openHistoryActivity()
    }

    override fun getById(id: Int?) {
        if (view == null) return
        storage.getExpressionById(id)
                .subscribeOn(workSchedulers)
                .observeOn(resultSchedulers)
                .subscribe(({ expression ->
                    view!!.showOnDisplay(expression.value)
                }), ({
                    view!!.showError("Error loading example by id")
                }))
    }
}