package xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.zeword.R
import xyz.thisisjames.boulevard.android.zeword.databinding.ChapterItemBinding
import xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders.PagerVH

class PageSelectAdapter (private val values :Int , private val onClick : (pos:Int)->Unit )
    : RecyclerView.Adapter<PagerVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH  =  PagerVH(
        ChapterItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = values-1

    override fun onBindViewHolder(holder: PagerVH, position: Int) {


         holder.textView.apply {
             text = if (position >= 9 ) "${position+1}" else "0${position+1}"
         }

        holder.itemView.setOnClickListener { onClick(position) }


    }

}