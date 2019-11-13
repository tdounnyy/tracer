package util

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.util.Log
import com.tbruyelle.rxpermissions2.RxPermissions
import duan.felix.tracer.ui.MainActivity

object PermissionUtils {

  const val CODE_EXTERNAL_STORAGE = 101
  const val TAG = "PermissionUtils"

  fun assureExternalStoragePermission(activity: MainActivity) {
    val rxPermissions = RxPermissions(activity);
    rxPermissions.request(WRITE_EXTERNAL_STORAGE)
      .subscribe { granted ->
        when (granted) {
          true -> Log.d(TAG, "Permission granted")
          false -> Log.d(TAG, "Permission denied")
        }
      }
//    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//      != PackageManager.PERMISSION_GRANTED
//    ) {
//      // Permission is not granted
//      ActivityCompat.requestPermissions(activity,
//        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//        CODE_EXTERNAL_STORAGE)
//    }
  }
}