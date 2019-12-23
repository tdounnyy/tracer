package duan.felix.tracer.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import duan.felix.tracer.R
import io.reactivex.disposables.Disposable
import util.Event
import util.PermissionUtils
import util.RxBus

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/13
 */
class MainActivity : AppCompatActivity() {

  private var subscription: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    PermissionUtils.assurePermissions(this)
    subscription = RxBus.observe(intArrayOf(Event.TRACE))
      .subscribe { Log.d(TAG, "received ${it.id} : ${it.obj}") }
  }

  override fun onDestroy() {
    super.onDestroy()
    subscription?.let {
      if (!it.isDisposed) {
        it.dispose()
      }
    }
  }

  companion object {
    const val TAG = "MainActivity"
  }
}