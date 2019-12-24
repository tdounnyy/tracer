package duan.felix.tracer.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import duan.felix.tracer.R
import duan.felix.tracer.entity.Trace
import io.reactivex.disposables.Disposable
import util.Event
import util.PermissionUtils
import util.RxBus

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/13
 */
class MainActivity : AppCompatActivity() {

  private var subscription: Disposable? = null
  private var fragment: Fragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    PermissionUtils.assurePermissions(this)

    if (fragment == null) {
      fragment = PickImageFragment().also {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_main, it)
          .commitAllowingStateLoss()
      }
    }

    subscription = RxBus.observe(intArrayOf(Event.TRACE))
      .subscribe {
        Log.d(TAG, "received ${it.id} : ${it.obj}")
        onReceiveTrace(it.obj as Trace)
      }
  }

  private fun onReceiveTrace(trace: Trace) {
    fragment = TraceFragment().apply {
      val args = Bundle()
      args.putParcelable(TraceFragment.ARG_TRACE, trace)
      arguments = args
    }.also {
      supportFragmentManager.beginTransaction().replace(R.id.fragment_main, it)
        .commitAllowingStateLoss()
    }
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