package com.njongjames.zeword.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.njongjames.zeword.Models.Bookz
import com.njongjames.zeword.databinding.BookItemBinding


class BooksItemRecyclerViewAdapter(
    private val values: ArrayList<Bookz>, private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<BooksItemRecyclerViewAdapter.ViewHolder>() {

    lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context;

        return ViewHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val item = values[position]
        holder.idView.text = item.abbrevation
        holder.nameView.text = item.book
        holder.chapView.text = "${item.chaptercount} Chaps"

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        val idView: TextView = binding.bbiAbbrev
        val chapView: TextView = binding.bbiChapters
        val nameView: TextView = binding.bbiName

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }


    }

}