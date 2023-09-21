package xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Groups
import xyz.thisisjames.boulevard.android.zeword.databinding.GroupsItemsBinding
import xyz.thisisjames.boulevard.android.zeword.listmanagers.viewholders.GroupsVH

class GroupsAdapter(private val values : List<Groups>, private val onItemClicked : (Groups)-> Unit)
    : RecyclerView.Adapter<GroupsVH>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsVH {
        return GroupsVH(
            GroupsItemsBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: GroupsVH, position: Int) {
        val item = values.get(position)

        if (item.header){
            holder.header.text = item.groupType
            holder.header.visibility = View.VISIBLE
        }

        holder.group.text = item.name

        holder.count.text = "${item.count} books"

        if (position == values.size-1){
            holder.footer.visibility = View.VISIBLE
        }

        holder.container.setOnClickListener {
            onItemClicked(item)
        }
    }
}