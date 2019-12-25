package duan.felix.tracer.usecase

import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.MediaType
import org.junit.Test

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/9
 */
class MediaUtilsTest {

  val media = Media("file://media", MediaType.Image, 0.0, 0.0, "title")
  @Test
  fun getRandomCalendar() {
    println(MediaUtils.getRandomCalendar(media).time)
    println(MediaUtils.getRandomCalendar(media).time)
    println(MediaUtils.getRandomCalendar(media).time)
  }

  @Test
  fun getRandomLatitude() {
    println(MediaUtils.getRandomLatitude(media))
    println(MediaUtils.getRandomLatitude(media))
    println(MediaUtils.getRandomLatitude(media))
  }

  @Test
  fun getRandomLongitude() {
    println(MediaUtils.getRandomLongitude(media))
    println(MediaUtils.getRandomLongitude(media))
    println(MediaUtils.getRandomLongitude(media))
  }
}