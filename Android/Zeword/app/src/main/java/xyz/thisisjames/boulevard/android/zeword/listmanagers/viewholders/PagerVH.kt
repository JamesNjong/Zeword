package xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import xyz.thisisjames.boulevard.android.zeword.databinding.ChapterItemBinding

class PagerVH (private val binding : ChapterItemBinding ) : ViewHolder(binding.root) {

    val textView : AppCompatTextView = binding.textView

}