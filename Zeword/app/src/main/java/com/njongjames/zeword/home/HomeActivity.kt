package com.njongjames.zeword.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.njongjames.zeword.Backend.Utilz
import com.njongjames.zeword.R
import com.njongjames.zeword.databinding.ActivityHomeBinding
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity  : AppCompatActivity() {
    var contentHasLoaded  = false

    //in the next line we declare variable to hold the splash screen
    private lateinit var splashScreen: androidx.core.splashscreen.SplashScreen

    //next we declare a variable to the loading json data
    // notice that we use the same variable across and thus use DI with hilt
    @Inject
    lateinit var primVM: PrimarilyViewModel

    private lateinit var binding: ActivityHomeBinding

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //next we are hiding screen decoration like status and navigation bar
        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions

        primVM = PrimarilyViewModel(utilz = Utilz(this@HomeActivity))
        setupSplashScreen()



        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)

        navView.setupWithNavController(navController)
    }
}