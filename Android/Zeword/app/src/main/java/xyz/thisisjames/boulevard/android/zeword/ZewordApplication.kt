package xyz.thisisjames.boulevard.android.zeword

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ZewordApplication : Application() {
    val myRepository : Application = this
}