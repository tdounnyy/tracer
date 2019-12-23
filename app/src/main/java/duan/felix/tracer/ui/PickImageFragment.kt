package duan.felix.tracer.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import duan.felix.tracer.R
import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.Spot
import duan.felix.tracer.usecase.ImagePicker
import duan.felix.tracer.usecase.SpotBuilder
import duan.felix.tracer.usecase.TraceBuilder

class PickImageFragment : Fragment() {

  val spotBuilder = SpotBuilder()
  val traceBuilder = TraceBuilder()
  val spots = mutableListOf<Spot>()
  private lateinit var imagePicker: ImagePicker

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    imagePicker = ImagePicker(this, REQUEST_CODE_PICK_IMAGE)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_pickimage, container, false).also {
      it.findViewById<Button>(R.id.btn_pick).setOnClickListener {
        imagePicker.pickMedia()
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
    private const val TAG = "PickImageFragment"
    const val REQUEST_CODE_PICK_IMAGE = 0
  }
}