package dmitry.kuzhelko.calculator.ui.main.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dmitry.kuzhelko.calculator.*
import dmitry.kuzhelko.calculator.App.Companion.appComponent
import dmitry.kuzhelko.calculator.ui.main.presenter.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenterImpl

    @Inject
    lateinit var calc: Calculator

    //main operations
    private var addition: Boolean = false
    private var subtract: Boolean = false
    private var multiplication: Boolean = false
    private var division: Boolean = false

    private var equals: Boolean = false

    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        presenter.onAttach(this)

        //show example by id from history
        showById()

        initDigitalButtons()

        initAddButton()
        initSubButton()
        initMulButton()
        initDivButton()
        initEqlButton()
        initClrButton()
        initHistButton()
    }

    override fun showById() {
        if (intent.hasExtra(EXAMPLE_ID)) {
            presenter.getById(intent.getIntExtra(EXAMPLE_ID, -1))
        }
    }

    override fun initDigitalButtons() {
        button1.setOnClickListener { display.text = "${getTextFromDisplay()}1" }
        button2.setOnClickListener { display.text = "${getTextFromDisplay()}2" }
        button3.setOnClickListener { display.text = "${getTextFromDisplay()}3" }
        button4.setOnClickListener { display.text = "${getTextFromDisplay()}4" }
        button5.setOnClickListener { display.text = "${getTextFromDisplay()}5" }
        button6.setOnClickListener { display.text = "${getTextFromDisplay()}6" }
        button7.setOnClickListener { display.text = "${getTextFromDisplay()}7" }
        button8.setOnClickListener { display.text = "${getTextFromDisplay()}8" }
        button9.setOnClickListener { display.text = "${getTextFromDisplay()}9" }
        button0.setOnClickListener { display.text = "${getTextFromDisplay()}0" }
    }

    override fun initAddButton() {
        buttonAdd.setOnClickListener {
            when (display) {
                null -> showOnDisplay(EMPTY_SCREEN)
                else -> {
                    if (!addition) {
                        expression = "${getTextFromDisplay()}$ADDITION"
                        showOnDisplay(expression)
                        addition = true
                    }
                }
            }
        }
    }

    override fun initSubButton() {
        buttonSub.setOnClickListener {
            when (display) {
                null -> showOnDisplay(EMPTY_SCREEN)
                else -> {
                    if (!subtract) {
                        expression = "${getTextFromDisplay()}$SUBTRACTION"
                        showOnDisplay(expression)
                        subtract = true
                    }
                }
            }
        }
    }

    override fun initMulButton() {
        buttonMul.setOnClickListener {
            when (display) {
                null -> showOnDisplay(EMPTY_SCREEN)
                else -> {
                    if (!multiplication) {
                        expression = "${getTextFromDisplay()}$MULTIPLICATION"
                        showOnDisplay(expression)
                        multiplication = true
                    }
                }
            }
        }
    }

    override fun initDivButton() {
        buttonDiv.setOnClickListener {
            when (display) {
                null -> showOnDisplay(EMPTY_SCREEN)
                else -> {
                    if (!division) {
                        expression = "${getTextFromDisplay()}$DIVISION"
                        showOnDisplay(expression)
                        division = true
                    }

                }
            }
        }
    }

    override fun initEqlButton() {
        buttonEql.setOnClickListener {
            if (!equals) {
                expression = getTextFromDisplay()
                val operands: List<String>

                when {
                    addition -> {
                        operands = expression.split(ADDITION)
                        expression = "$expression=${calc.addition(operands[0], operands[1])}"
                    }
                    subtract -> {
                        operands = expression.split(SUBTRACTION)
                        expression = "$expression=${calc.subtract(operands[0], operands[1])}"

                    }
                    multiplication -> {
                        operands = expression.split(MULTIPLICATION)
                        expression = "$expression=${calc.multiply(operands[0], operands[1])}"
                    }
                    division -> {
                        operands = expression.split(DIVISION)
                        expression = "$expression=${calc.multiply(operands[0], operands[1])}"
                    }
                }
                showOnDisplay(expression)
                disableMultiClick()
                if (expression != EMPTY_SCREEN) {
                    saveToDb(expression)
                }
            }
        }
    }

    override fun initClrButton() {
        btnClear.setOnClickListener {
            showOnDisplay(EMPTY_SCREEN)
            enableClick()
        }
    }

    override fun initHistButton() {
        btnHistory.setOnClickListener { showHistory() }
    }

    override fun showOnDisplay(text: String) {
        display.text = text
    }

    override fun getTextFromDisplay() = display.text.toString()

    override fun saveToDb(example: String) {
        presenter.saveToDb(example)
    }

    override fun showHistory() {
        presenter.showHistory()
    }

    override fun disableMultiClick() {
        equals = true
        addition = true
        subtract = true
        multiplication = true
        division = true
    }

    override fun enableClick() {
        addition = false
        subtract = false
        multiplication = false
        division = false
        equals = false
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}