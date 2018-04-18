package dmitry.kuzhelko.calculator.router

import android.content.Context
import android.content.Intent
import dmitry.kuzhelko.calculator.EXAMPLE_ID
import dmitry.kuzhelko.calculator.ui.history.view.HistoryActivity
import dmitry.kuzhelko.calculator.ui.main.view.MainActivity

class RouterImpl(private val context: Context) : Router {

    override fun openHistoryActivity() {
        context.startActivity(Intent(context, HistoryActivity::class.java))
    }

    override fun openMainActivity(id: Int?) {
        context.startActivity(Intent(context, MainActivity::class.java)
                .putExtra(EXAMPLE_ID, id))
    }
}