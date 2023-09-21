package xyz.thisisjames.boulevard.android.zeword.frontend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.WindowCompat
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.zeword.databinding.ActivityMainBinding
import xyz.thisisjames.boulevard.android.zeword.frontend.home.HomeActivity


class MainActivity : AppCompatActivity() {

    private val getRunnable : ()->Runnable ={ mRunnable  }

    private val setProgress : ()->Unit = {
        progressCount =3000

        circularProgress.setCurrentProgress(progressCount.toDouble());

    }

    private val mRunnable : Runnable = Runnable {
        if (progressCount == 3000){
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Animatoo.animateInAndOut(this);
        }else{
            setProgress()
            mHandler.postDelayed(getRunnable.invoke(),2000)
        }
    }

    private val mHandler  = Handler()

    private var progressCount = 30

    private var _binding : ActivityMainBinding ? = null
    private val binding : ActivityMainBinding get() = _binding !!


    private lateinit var circularProgress : CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val decorview = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
       decorview.systemUiVisibility = uiOptions

        circularProgress = binding.circularProgress
        circularProgress.setMaxProgress(3000.0)

        mHandler.postDelayed(mRunnable, 300)
    }

    override fun onDestroy() {
        super.onDestroy()

        mHandler.removeCallbacks(mRunnable)
    }
}