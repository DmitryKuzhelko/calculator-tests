package dmitry.kuzhelko.calculator

import dmitry.kuzhelko.calculator.router.Router
import dmitry.kuzhelko.calculator.storage.LocalStorage
import dmitry.kuzhelko.calculator.storage.entity.Expression
import dmitry.kuzhelko.calculator.ui.main.presenter.MainPresenter
import dmitry.kuzhelko.calculator.ui.main.presenter.MainPresenterImpl
import dmitry.kuzhelko.calculator.ui.main.view.MainView
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


@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    private lateinit var presenter: MainPresenter<MainView>

    @Mock
    private lateinit var mockStorage: LocalStorage

    @Mock
    private lateinit var mockView: MainView

    @Mock
    private lateinit var mockRouter: Router

    private val TEST_ID = 1

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenterImpl(mockStorage, mockRouter, Schedulers.trampoline(), Schedulers.trampoline())
        presenter.onAttach(mockView)
    }


    //тестируем успешное выполнение метода getById(TEST_ID)
    @Test
    @Throws(Exception::class)
    fun getByIdSuccess() {
        `when`(mockStorage.getExpressionById(TEST_ID)).thenReturn(Single.just(Expression("2+2=4")))

        presenter.getById(TEST_ID)

        verify(mockView).showOnDisplay("2+2=4")
        verify(mockView, never()).showError(anyString());
    }

    //тестируем ошибку в getById()
    @Test
    @Throws(Exception::class)
    fun getByIdFailed() {
        val error = "Error loading example by id"

        `when`(mockStorage.getExpressionById(TEST_ID)).thenReturn(Single.error(Exception(error)))

        presenter.getById(TEST_ID)

        verify(mockView).showError(error)
        verify(mockView, never()).showOnDisplay(ArgumentMatchers.anyString())
    }

    //тестируем ситуацию, когда view отсоединена от презентера
    @Test
    @Throws(Exception::class)
    fun getByIdWithoutView() {
        presenter.onDetach()
        presenter.getById(TEST_ID)

        verify(mockStorage, never()).getExpressionById(TEST_ID)
        verify(mockView, never()).showOnDisplay(ArgumentMatchers.anyString())
    }
}