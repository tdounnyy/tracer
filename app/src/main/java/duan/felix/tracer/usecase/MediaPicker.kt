package duan.felix.tracer.usecase

import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.MediaType

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/9
 */
interface MediaPicker {

  fun pickMedia(): List<Media>

}

object MockMediaPicker : MediaPicker {

  override fun pickMedia(): List<Media> = listOf(Media("file://mocked_media", MediaType.Image))

}