package util

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.tbruyelle.rxpermissions2.RxPermissions

object PermissionUtils {

  const val TAG = "PermissionUtils"

  fun assurePermissions(activity: FragmentActivity) {
    val rxPermissions = RxPermissions(activity);
    rxPermissions.request(
      WRITE_EXTERNAL_STORAGE,
      ACCESS_FINE_LOCATION
    ).subscribe { granted ->
      when (granted) {
        true -> Log.d(TAG, "Permission granted")
        false -> Log.d(TAG, "Permission denied")
      }
    }
  }
}
