package dmitry.kuzhelko.calculator.ui.main.view

interface MainView {

    fun initDigitalButtons()

    fun initAddButton()
    fun initSubButton()
    fun initMulButton()
    fun initDivButton()

    fun initEqlButton()
    fun initClrButton()
    fun initHistButton()

    fun showById()

    fun saveToDb(example: String)

    fun showHistory()

    fun showOnDisplay(text: String)

    fun getTextFromDisplay(): String

    fun disableMultiClick()

    fun enableClick()

    fun showError(error: String)
}