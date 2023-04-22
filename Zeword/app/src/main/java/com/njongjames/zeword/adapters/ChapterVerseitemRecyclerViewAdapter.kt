package com.njongjames.zeword.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Space
import android.widget.TextView
import androidx.constraintlayout.motion.widget.Key.VISIBILITY
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.njongjames.zeword.Models.ClickedVerse
import com.njongjames.zeword.Models.LoadableVerse
import com.njongjames.zeword.databinding.VerseItemBinding
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE


class ChapterVerseitemRecyclerViewAdapter(
    private val values: LoadableVerse):
    RecyclerView.Adapter<ChapterVerseitemRecyclerViewAdapter.ViewHolder>(){

    var onCheckChanged: ((cv:ClickedVerse) -> Unit)? = null
    var onItemLongClick: ((cv:ClickedVerse) -> Unit)?= null

    inner class ViewHolder(binding:VerseItemBinding) : RecyclerView.ViewHolder(binding.root)  {

        val verse : TextView = binding.verse
        val button : CheckBox = binding.verseSelectButton
        val space:Space = binding.cardSpace

        init {
            binding.root.setOnLongClickListener{
                var cv = ClickedVerse(adapterPosition , true,values.verses[adapterPosition])
                onItemLongClick?.invoke(cv)
                return@setOnLongClickListener true
            }

            button.setOnCheckedChangeListener{_, isChecked->
                var cv = ClickedVerse(adapterPosition , isChecked,values.verses[adapterPosition])
                onCheckChanged?.invoke(cv)
            }


        }

    }

    lateinit var context: Context
    var lastItem:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        lastItem =  values.verses.size

        return ViewHolder(
            VerseItemBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return values.verses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.verse.text = "[${position+1}] ${values.verses[position]}"

        holder.button.apply {
            isVisible = values.viewStyle
            isChecked = values.checkStates[position].status
        }

        holder.space.isVisible = (position == lastItem-1)


    }


}