package com.schonfeld.virtus.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.schonfeld.virtus.R
import com.schonfeld.virtus.database.HitTable
import com.schonfeld.virtus.toReadableDate
import java.util.function.Consumer

class HitsAdapter(
    private val list: List<HitTable>,
    private val callbackClick: Consumer<HitTable>,
    private val callbackDelete: Consumer<HitTable>) : RecyclerSwipeAdapter<HitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], callbackClick, callbackDelete)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.hit_item, parent, false)) {

        private var swipeLayout: SwipeLayout = itemView.findViewById(R.id.swipe)
        private var btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        private var mTitle: TextView = itemView.findViewById(R.id.mTitle)
        private var mSubtitle: TextView = itemView.findViewById(R.id.mSubtitle)
        private var mCard: CardView = itemView.findViewById(R.id.mCard)

        fun bind(hit: HitTable, callbackClick: Consumer<HitTable>, callbackDelete: Consumer<HitTable>) {
            swipeLayout.showMode = SwipeLayout.ShowMode.PullOut

            mTitle.text = hit.title
            mSubtitle.text = """${hit.author} - ${hit.created.toReadableDate()}"""
            mCard.setOnClickListener {
                callbackClick.accept(hit)
            }
            btnDelete.setOnClickListener {
                callbackDelete.accept(hit)
            }
        }
    }
}