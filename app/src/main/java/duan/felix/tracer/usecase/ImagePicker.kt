package duan.felix.tracer.usecase

import android.app.Activity
import android.content.Intent
import android.net.Uri
import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.MediaType

class ImagePicker(val activity: Activity, val requestCode: Int) : MediaPicker {
  override fun pickMedia() {
    with(activity) {
      val intent = Intent(Intent.ACTION_GET_CONTENT)
        .apply {
          type = "image/*"
          putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
      startActivityForResult(intent, requestCode)
    }
  }

  fun onMediaPicked(data: Intent?): List<Media> {
    val urls = mutableListOf<Uri>()
    data?.let {
      it.data?.let { url -> urls.add(url) }
    }
    data?.clipData?.let {
      val last = it.itemCount - 1
      for (i in 0..last) {
        urls.add(it.getItemAt(i).uri)
      }
    }
    return onMediaPicked(urls)
  }

  override fun onMediaPicked(urls: List<Uri>): List<Media> {
    val list = mutableListOf<Media>()
    for (url in urls) {
      list.add(Media(url.toString(), MediaType.Image))
    }
    return list
  }
}