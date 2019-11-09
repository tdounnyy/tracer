package duan.felix.tracer.usecase

import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.Spot

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/9
 */
class SpotBuilder {

  fun parse(media: Media): Spot {
    val calendar = MediaUtils.getRandomCalendar(media)
    val latitude = MediaUtils.getRandomLatitude(media)
    val longitude = MediaUtils.getRandomLongitude(media)
    return Spot(calendar, latitude, longitude)
  }
}