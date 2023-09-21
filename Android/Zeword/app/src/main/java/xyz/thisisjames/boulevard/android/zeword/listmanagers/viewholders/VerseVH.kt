package xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders

import android.widget.CheckBox
import android.widget.Space
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.zeword.Backend.data.ClickedVerse
import xyz.thisisjames.boulevard.android.zeword.databinding.VerseItemBinding

class VerseVH (binding: VerseItemBinding) : RecyclerView.ViewHolder(binding.root)  {

    val verse : TextView = binding.verse
    val button : CheckBox = binding.verseSelectButton
    val space: Space = binding.cardSpace

}