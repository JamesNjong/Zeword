package xyz.thisisjames.boulevard.android.zeword.frontend.reader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.zeword.R
import xyz.thisisjames.boulevard.android.zeword.databinding.ActivityReaderBinding
import xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.aids.SectionsPagerAdapter
import xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.sheets.ActionSheet
import xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.sheets.BookSheet
import xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.sheets.ChapterSheet
import javax.inject.Inject

@AndroidEntryPoint
class ReaderActivity : AppCompatActivity() {

    @Inject
    lateinit var primVM: PrimarilyViewModel
    private lateinit var binding: ActivityReaderBinding

    private lateinit var viewPager : ViewPager
    private lateinit var sectionPagerAdapter : SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        binding = ActivityReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        if (primVM.focusGroup.size>1) setupDrawer()

        binding.spinner.setOnClickListener { setupDrawer() }

        binding.backButton.setOnClickListener { finish() }

        primVM.currentBook.observe(this, Observer {
            binding.bookName.text = it.book
            setupViewPager(it.chaptercount!!)
        })

        primVM.currentPosition.observe(this, Observer {
            binding.bookChapter.text = "Chapter ${it+1}"
            viewPager.currentItem = it
        })



        primVM.fabFunctionSwitch.observe(this, Observer {
            if (it == 1){
                //fab is turned on for verse selection

                binding.fab.apply {

                    setImageDrawable(resources.getDrawable(R.drawable.luna_tick))
                    setOnClickListener {
                        val sheet = ActionSheet()
                        sheet.show(supportFragmentManager, "BSVerseActionDialogFragment")
                    }
                }

            }else{
                //fab is in its default state
                binding.fab.apply {

                    setImageDrawable(resources.getDrawable(R.drawable.luna_grid))
                    setOnClickListener {
                        val sheet = ChapterSheet()
                        sheet.show(supportFragmentManager, "BSChapterSelectorDialogFragment")
                    }

                }
            }
        })
        primVM.switchFabFunction(0)

        viewPager = findViewById(R.id.viewPager)


    }




    fun setupDrawer () {
        val bottomSheetFragment = BookSheet()
        bottomSheetFragment.show(supportFragmentManager, "BSDialogFragment")
    }

    fun setupViewPager(it:Int){
        sectionPagerAdapter = SectionsPagerAdapter(it,supportFragmentManager)
        viewPager.apply { adapter = sectionPagerAdapter

            addOnPageChangeListener(object:OnPageChangeListener{
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    binding.bookChapter.text = "Chapter ${position+1}"
                }

                override fun onPageSelected(position: Int) {

                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })
        }
    }



}