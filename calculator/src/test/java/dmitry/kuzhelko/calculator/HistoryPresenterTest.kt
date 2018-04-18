package dmitry.kuzhelko.calculator

import dmitry.kuzhelko.calculator.router.Router
import dmitry.kuzhelko.calculator.storage.LocalStorage
import dmitry.kuzhelko.calculator.storage.entity.Expression
import dmitry.kuzhelko.calculator.ui.history.presenter.HistoryPresenter
import dmitry.kuzhelko.calculator.ui.history.presenter.HistoryPresenterImpl
import dmitry.kuzhelko.calculator.ui.history.view.HistoryView
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HistoryPresenterTest {

    private lateinit var presenter: HistoryPresenter<HistoryView>

    @Mock
    private lateinit var mockStorage: LocalStorage

    @Mock
    private lateinit var mockView: HistoryView

    @Mock
    private lateinit var mockRouter: Router

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = HistoryPresenterImpl(mockStorage, mockRouter, Schedulers.trampoline(), Schedulers.trampoline())
        presenter.onAttach(mockView)
    }

    private fun getFakeExpressionsList(): List<Expression> {
        val expressions = ArrayList<Expression>()
        expressions.add(Expression("1+1=2"))
        expressions.add(Expression("2*3=6"))
        return expressions
    }

    //тестируем успешное выполнение метода loadAllExamples()
    @Test
    @Throws(Exception::class)
    fun loadAllExamplesSuccess() {
        val fakeExpressions = getFakeExpressionsList()
        `when`(mockStorage.loadAllExpression()).thenReturn(Single.just(fakeExpressions))

        presenter.loadAllExamples()

        verify(mockView).showAllExamples(fakeExpressions)
    }

    //тестируем ошибку в loadAllExamples()
    @Test
    @Throws(Exception::class)
    fun loadAllExamplesFailed() {
        val error = "Error loading"
        `when`(mockStorage.loadAllExpression()).thenReturn(Single.error(Exception(error)))

        presenter.loadAllExamples()

        verify(mockView).showError(error)
        verify(mockView, never()).showAllExamples(ArgumentMatchers.anyList<Expression>())
    }

    //тестируем ситуацию, когда view отсоединена от презентера
    @Test
    @Throws(Exception::class)
    fun loadAllExamplesWithoutView() {
        presenter.onDetach()
        presenter.loadAllExamples()

        verify(mockStorage, never()).loadAllExpression()
        verify(mockView, never()).showAllExamples(ArgumentMatchers.anyList<Expression>())
    }
}