package xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.zeword.databinding.BookItemBinding

class BooksVH (binding : BookItemBinding) : RecyclerView.ViewHolder(binding.root)  {

    val textView : AppCompatTextView = binding.bbiName

    val abrev : AppCompatTextView =   binding.bbiAbbrev

}