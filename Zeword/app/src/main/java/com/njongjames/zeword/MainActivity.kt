package com.njongjames.zeword

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.njongjames.zeword.databinding.ActivityMainBinding
import com.njongjames.zeword.Backend.Utilz
import com.njongjames.zeword.di.PrimarilyViewModel
import com.njongjames.zeword.ui.MainSectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var splashScreen: androidx.core.splashscreen.SplashScreen

    private var contentHasLoaded: Boolean = false
    private lateinit var binding: ActivityMainBinding

    lateinit var allBooks: TextView
    lateinit var oldBooks: TextView
    lateinit var newBooks: TextView

    @Inject
    lateinit var primVM: PrimarilyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions

        primVM = PrimarilyViewModel(utilz = Utilz(this@MainActivity))
        setupSplashScreen()


        val viewPager:ViewPager = binding.booksPager
        val sectionsPager = MainSectionsPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPager

        allBooks = binding.allBooks
        allBooks.setOnClickListener{
            viewPager.setCurrentItem(0)
        }
        oldBooks = binding.oldBooks
        oldBooks.setOnClickListener{viewPager.setCurrentItem(1)}
        newBooks = binding.newBooks
        newBooks.setOnClickListener{viewPager.setCurrentItem(2)}

        viewPager.addOnPageChangeListener(  object : OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when(position){
                    0->{
                        allBooks.background =  getDrawable(R.drawable.selected_tab_indicator)
                        oldBooks.background = getDrawable(R.color.off_white1)
                        newBooks.background = getDrawable(R.color.off_white1)
                        allBooks.setTextColor(getColor(R.color.off_white1))
                        oldBooks.setTextColor(getColor(R.color.text_secondary))
                        newBooks.setTextColor(getColor(R.color.text_secondary))

                        oldBooks.text = "OT"
                        newBooks.text = "NT"
                    }
                    1->{
                        oldBooks.background =  getDrawable(R.drawable.selected_tab_indicator)
                        allBooks.background = getDrawable(R.color.off_white1)
                        newBooks.background = getDrawable(R.color.off_white1)
                        oldBooks.setTextColor(getColor(R.color.off_white1))
                        allBooks.setTextColor(getColor(R.color.text_secondary))
                        newBooks.setTextColor(getColor(R.color.text_secondary))

                        oldBooks.text = "Old Testament"
                        newBooks.text = "NT"
                    }
                    else->{
                        newBooks.background =  getDrawable(R.drawable.selected_tab_indicator)
                        oldBooks.background = getDrawable(R.color.off_white1)
                        allBooks.background = getDrawable(R.color.off_white1)
                        newBooks.setTextColor(getColor(R.color.off_white1))
                        oldBooks.setTextColor(getColor(R.color.text_secondary))
                        allBooks.setTextColor(getColor(R.color.text_secondary))
                        oldBooks.text = "OT"
                        newBooks.text = "New Testament"
                    }
                }
            }
            override fun onPageSelected(position: Int) {
            }

        })

        binding.toggleMenu.setOnClickListener{
            binding.toolbar.visibility = GONE
            binding.expandedMenu.visibility = VISIBLE
        }

        binding.backArrow.setOnClickListener{
            binding.toolbar.visibility = VISIBLE
            binding.expandedMenu.visibility = GONE
        }
    }


    var myHandler: Handler = Handler()
    var myRunnable = Runnable {
        contentHasLoaded = true
    }
    private fun setupSplashScreen(){
        myHandler.postDelayed(myRunnable, 1000);

        val content: View = binding.root
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (contentHasLoaded) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else false
                }
            }
        )


    }

    private fun tabManager(){

    }

}