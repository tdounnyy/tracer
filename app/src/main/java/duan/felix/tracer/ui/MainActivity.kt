package duan.felix.tracer.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import duan.felix.tracer.R
import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.Spot
import duan.felix.tracer.usecase.ImagePicker
import duan.felix.tracer.usecase.SpotBuilder
import duan.felix.tracer.usecase.TraceBuilder
import util.PermissionUtils

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/13
 */
class MainActivity : AppCompatActivity() {

  val spotBuilder = SpotBuilder()
  val traceBuilder = TraceBuilder()
  val spots = mutableListOf<Spot>()
  val imagePicker = ImagePicker(this, REQUEST_CODE_PICK_IMAGE)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    PermissionUtils.assureExternalStoragePermission(this)
    findViewById<Button>(R.id.btn_pick).setOnClickListener {
      imagePicker.pickMedia()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
      handlePickImageResult(data)
    }
  }

  private fun handlePickImageResult(data: Intent?) {
    Log.d(TAG, "data = ${data?.data}")
    val medias = imagePicker.onMediaPicked(data)
    parseMedia2Spots(medias, spots)
    traceBuilder.buildTrace(spots)?.let {
      Log.d(TAG, "Trace is built: $it")
    }
  }

  private fun parseMedia2Spots(list: List<Media>, spots: MutableList<Spot>) {
    for (media in list) {
      spotBuilder.emit(media)?.let { spot ->
        spots.add(spot)
      }
    }
  }

  companion object {
    private const val TAG = "MainActivity"
    const val REQUEST_CODE_PICK_IMAGE = 0
  }
}