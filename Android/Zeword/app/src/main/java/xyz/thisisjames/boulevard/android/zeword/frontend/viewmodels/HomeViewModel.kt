package xyz.thisisjames.boulevard.android.zeword.frontend.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Groups
import xyz.thisisjames.boulevard.android.zeword.R

class HomeViewModel : ViewModel() {
    fun generateList (context : Context):List<Groups>{

        var groupList :ArrayList<Groups> = arrayListOf()

        val ot = Groups(context.getString(R.string.groups), context.getString(R.string.old_testament),46,true)
        groupList.add(ot)
        
        val nt = Groups(context.getString(R.string.groups), context.getString(R.string.new_testament),27, false)
        groupList.add(nt)
        
        val gospels =  Groups(context.getString(R.string.sub_groups), context.getString(R.string.the_gospels), 4, true)
        groupList.add(gospels)
        
        val deut = Groups(context.getString(R.string.sub_groups), context.getString(R.string.the_acts_of_the_apostles), 1)
        groupList.add(deut)
        
        val law = Groups(context.getString(R.string.sub_groups), context.getString(R.string.the_pentateuch),5)
        groupList.add(law)
        
        val hist = Groups(context.getString(R.string.sub_groups), context.getString(R.string.historical_books), 16)
        groupList.add(hist)
        
        val wis = Groups(context.getString(R.string.sub_groups), context.getString(R.string.wisdom_books),7)
        groupList.add(wis)
        
        val prof = Groups(context.getString(R.string.sub_groups), context.getString(R.string.major_prophets),6)
        groupList.add(prof)
        
        val mino = Groups(context.getString(R.string.sub_groups), context.getString(R.string.minor_prophets),11)
        groupList.add(mino)
        
        val epis = Groups(context.getString(R.string.sub_groups), context.getString(R.string.pauline_epistles), 13)
        groupList.add(epis)
        
        val cath = Groups(context.getString(R.string.sub_groups), context.getString(R.string.hebrews_catholic_letters), 8)
        groupList.add(cath)

        groupList.add(Groups(context.getString(R.string.sub_groups), context.getString(R.string.the_book_of_revelation),1))

        return groupList
    }
}