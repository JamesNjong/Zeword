package xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.zeword.databinding.GroupsItemsBinding

class GroupsVH (binding : GroupsItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {

        val header : AppCompatTextView = binding.header
        val group : AppCompatTextView = binding.group
        val count : AppCompatTextView = binding.count

        val footer : View = binding.footer

        val container : View = binding.container
}