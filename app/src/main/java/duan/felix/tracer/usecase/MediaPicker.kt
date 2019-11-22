package duan.felix.tracer.usecase

import android.net.Uri
import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.MediaType

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/9
 */
interface MediaPicker {
  fun pickMedia()
  fun onMediaPicked(urls: List<Uri>): List<Media>
}

object MockMediaPicker : MediaPicker {

  override fun pickMedia() {
    // no-op
  }

  override fun onMediaPicked(urls: List<Uri>): List<Media> {
    return listOf(Media("file://mocked_media", MediaType.Image))
  }
}