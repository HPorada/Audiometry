package edu.ib.audiometry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultsAdapter(
    mResult: ArrayList<Result>?,
    mOnClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {
    class ViewHolder(
        itemView: View,
        onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var text: TextView
        var listener: OnItemClickListener

        override fun onClick(v: View) {
            listener.onItemClick(v, layoutPosition)
        }

        init {
            text = itemView.findViewById<View>(R.id.tvItem) as TextView
            listener = onItemClickListener
            itemView.setOnClickListener(this)
        }
    }

    private val mResult: List<Result>
    private val mOnClickListener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val resultView: View = inflater.inflate(R.layout.item_result, parent, false)
        return ViewHolder(resultView, mOnClickListener)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val result: Result = mResult[position]
        val textView = holder.text
        textView.setText(result.getCreated().toString())
    }

    override fun getItemCount(): Int {
        return mResult.size
    }

    init {
        this.mResult = mResult as List<Result>
        this.mOnClickListener = mOnClickListener
    }
}