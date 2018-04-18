package dmitry.kuzhelko.calculator.ui.history.presenter

import dmitry.kuzhelko.calculator.router.Router
import dmitry.kuzhelko.calculator.storage.LocalStorage
import dmitry.kuzhelko.calculator.ui.history.view.HistoryView
import io.reactivex.Scheduler
import javax.inject.Inject

class HistoryPresenterImpl
@Inject constructor(private val storage: LocalStorage,
                    private val router: Router,
                    private val workSchedulers: Scheduler,
                    private val resultSchedulers: Scheduler) : HistoryPresenter<HistoryView> {

    private var view: HistoryView? = null

    override fun onAttach(mvpView: HistoryView) {
        this.view = mvpView
    }

    override fun onDetach() {
        view = null
    }

    override fun loadAllExamples() {
        if (view == null) return
        storage.loadAllExpression()
                .subscribeOn(workSchedulers)
                .observeOn(resultSchedulers)
                .subscribe(({ expressions ->
                    view!!.showAllExamples(expressions)
                }), ({
                    view!!.showError("Error loading")
                }))
    }

    override fun openMainActivity(id: Int?) {
        router.openMainActivity(id)
    }
}
