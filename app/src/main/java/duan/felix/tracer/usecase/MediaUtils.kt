package duan.felix.tracer.usecase

import duan.felix.tracer.entity.Media
import java.util.*

object MediaUtils {
  fun getRandomCalendar(media: Media): Calendar = Calendar.getInstance().apply {
    set(Random().nextInt(2019), Random().nextInt(12),
      Random().nextInt(28), 0, 0, 0)
  }

  fun getRandomLatitude(media: Media): Double = Random().nextInt(180).run { this - 90.0 }

  fun getRandomLongitude(media: Media): Double = Random().nextInt(360).run { this - 180.0 }

}
