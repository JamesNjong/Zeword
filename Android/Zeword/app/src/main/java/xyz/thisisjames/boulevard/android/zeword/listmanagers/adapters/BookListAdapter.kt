package xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Bookz
import xyz.thisisjames.boulevard.android.zeword.databinding.BookItemBinding
import xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders.BooksVH

class BookListAdapter (private val values: List<Bookz>, private val onItemClicked : (book : Bookz)->Unit)
    : RecyclerView.Adapter<BooksVH>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksVH
    = BooksVH(BookItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    override fun getItemCount(): Int = values.size


    override fun onBindViewHolder(holder: BooksVH, position: Int) {
        val item = values[position]
         holder.textView.text = item.book

        holder.abrev.text = item.abbrevation

        holder.itemView.setOnClickListener { onItemClicked(item) }
    }


}