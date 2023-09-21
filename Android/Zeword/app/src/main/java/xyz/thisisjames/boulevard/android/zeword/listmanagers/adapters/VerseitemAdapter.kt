package xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters

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
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.zeword.Backend.data.ClickedVerse
import xyz.thisisjames.boulevard.android.zeword.Backend.data.LoadableVerse
import xyz.thisisjames.boulevard.android.zeword.databinding.VerseItemBinding
import xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders.VerseVH


class VerseitemAdapter(
    private val values: LoadableVerse, private val onCLick : (ClickedVerse)->Unit, private val onLongClick: (ClickedVerse)->Unit
):
    RecyclerView.Adapter<VerseVH>(){



    lateinit var context: Context
    var lastItem:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseVH {
        context = parent.context
        lastItem =  values.verses.size

        return VerseVH(
            VerseItemBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return values.verses.size
    }

    override fun onBindViewHolder(holder: VerseVH, position: Int) {

        val item = values.verses[position]

        holder.verse.text = "[${position+1}] ${values.verses[position]}"

        holder.button.apply {
            isVisible = values.viewStyle
            isChecked = values.checkStates[position].status

            setOnCheckedChangeListener{_, isChecked ->
                var cv = ClickedVerse(position , isChecked,item)
                onCLick(cv)
            }
        }

        holder.space.isVisible = (position == lastItem-1)

        holder.itemView.setOnClickListener { onCLick(ClickedVerse(position,true,item)) }

        holder.itemView.setOnLongClickListener {
            var cv = ClickedVerse(position , true,item)
            onLongClick(cv)
            return@setOnLongClickListener true
        }

    }


}