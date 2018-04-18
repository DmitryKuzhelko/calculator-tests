package dmitry.kuzhelko.calculator.ui.history

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitry.kuzhelko.calculator.R
import dmitry.kuzhelko.calculator.storage.entity.Expression
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class HistoryAdapter(context: Context) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var examples: List<Expression> = emptyList()
    private lateinit var clickListener: ClickListener
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    interface ClickListener {
        fun onClick(example: Expression)
    }

    fun updateAdapter(examples: List<Expression>) {
        this.examples = examples
        notifyDataSetChanged()
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindNews(examples[position])
    }

    override fun getItemCount(): Int = if (examples.isEmpty()) 0 else examples.size

    inner class HistoryViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindNews(example: Expression) {
            tvExample.text = example.value
        }

        init {
            itemView.setOnClickListener {
                clickListener.onClick(examples[adapterPosition])
            }
        }
    }
}