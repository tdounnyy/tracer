package duan.felix.tracer.entity

import junit.framework.Assert.assertEquals
import org.junit.Test

class MediaTest {

  val media = Media("file://a_bravo_photo", MediaType.Image)

  @Test
  fun getUrl() {
    assertEquals("file://a_bravo_photo", media.url)
  }

  @Test
  fun getMediaType() {
    assertEquals(MediaType.Image, media.mediaType)
  }

  @Test
  fun print() {
    print(media)
  }
}