package duan.felix.tracer.usecase

import android.net.Uri
import android.util.Log
import duan.felix.tracer.app.TracerApp
import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.MemMediaRepo
import duan.felix.tracer.entity.Spot
import java.text.SimpleDateFormat
import java.util.*

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

  fun emit(media: Media): Spot? {
    val context = TracerApp.appContext
    if (!MediaUtils.isValidMedia(context, media)) {
      return null
    }
    if (!MemMediaRepo.addMedia(media)) {
      return null
    }
    val latlong = MediaUtils.getImageLatLong(context, Uri.parse(media.url))
    val datetime = MediaUtils.getDatetime(context, Uri.parse(media.url))
    if (latlong == null) {
      Log.d(TAG, "fastfail latlong = $latlong & datetime = $datetime")
      return null
    }
    if (datetime == null) {
      Log.d(TAG, "fastfail latlong = $latlong & datetime = $datetime")
      return null
    }
    val cal = Calendar.getInstance().apply {
      time = SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(datetime)
    }
    return Spot(cal, latlong[0], latlong[1])
  }

  companion object {
    const val TAG = "SpotBuilder"
  }
}