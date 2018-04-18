package dmitry.kuzhelko.calculator.ui.main.view

class Calculator {

    fun addition(a: String, b: String) = a.toInt() + b.toInt()

    fun subtract(a: String, b: String) = a.toInt() - b.toInt()

    fun multiply(a: String, b: String) = a.toInt() * b.toInt()

    fun divide(a: String, b: String): Int {
        when (b.toInt()) {
            0 -> return 0
            else -> return a.toInt() / b.toInt()
        }
    }
}