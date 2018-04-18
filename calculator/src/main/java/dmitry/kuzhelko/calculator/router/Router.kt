package dmitry.kuzhelko.calculator.router

interface Router {
    fun openHistoryActivity()

    fun openMainActivity(id: Int?)
}