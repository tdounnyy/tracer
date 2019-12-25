package duan.felix.tracer.entity

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MediaTest {

  val media = Media("file://a_bravo_photo", MediaType.Image, 0.0, 0.0, "title")

  @Test
  fun getUrl() {
    assertThat(media.url).isEqualTo("file://a_bravo_photo")
  }

  @Test
  fun getMediaType() {
    assertThat(media.mediaType).isEqualTo(MediaType.Image)
  }

  @Test
  fun print() {
    print(media)
  }
}