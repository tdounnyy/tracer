package duan.felix.tracer.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import duan.felix.tracer.entity.Trace
import duan.felix.tracer.gallery.ui.GalleryFragment
import io.reactivex.disposables.Disposable
import util.Event
import util.PermissionUtils
import util.RxBus


/**
 * @author duanyufei@dayuwuxian.com at 2019/11/13
 */
class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

  private var subscription: Disposable? = null
  private var fragment: Fragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(duan.felix.tracer.R.layout.activity_main)

    setSupportActionBar(findViewById(duan.felix.tracer.R.id.my_toolbar))
    supportFragmentManager.addOnBackStackChangedListener(this)
    shouldDisplayHomeUp()

    PermissionUtils.assurePermissions(this)

    if (fragment == null) {
      fragment = GalleryFragment().also {
        supportFragmentManager
          .beginTransaction()
          .replace(duan.felix.tracer.R.id.fragment_main, it)
          .commit()
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
      supportFragmentManager
        .beginTransaction()
        .replace(duan.felix.tracer.R.id.fragment_main, it)
        .addToBackStack("pick")
        .commit()
    }
  }

  override fun onBackStackChanged() {
    shouldDisplayHomeUp()
  }

  private fun shouldDisplayHomeUp() {
    //Enable Up button only  if there are entries in the back stack
    val canGoBack = supportFragmentManager.backStackEntryCount > 0
    supportActionBar!!.setDisplayHomeAsUpEnabled(canGoBack)
  }

  override fun onSupportNavigateUp(): Boolean {
    supportFragmentManager.popBackStack()
    return true
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