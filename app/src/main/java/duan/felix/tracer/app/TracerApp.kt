package duan.felix.tracer.app

import android.app.Application
import android.content.Context

class TracerApp : Application() {

  companion object {
    lateinit var appContext: Context
  }

  override fun onCreate() {
    super.onCreate()
    appContext = applicationContext
  }
}