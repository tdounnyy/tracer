package duan.felix.tracer.usecase

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import androidx.exifinterface.media.ExifInterface
import androidx.exifinterface.media.ExifInterface.TAG_DATETIME_ORIGINAL
import duan.felix.tracer.entity.Media
import java.io.FileNotFoundException
import java.util.*

object MediaUtils {

  private const val TAG = "MediaUtils"

  //TODO: Video EXIF data
  fun getImageLatLong(context: Context, data: Uri): DoubleArray? {
    return context.contentResolver.openInputStream(data)?.let {
      val exif = ExifInterface(it)
      val latlong = exif.getLatLong()
      it.close()
      latlong
    }.also {
      when (it) {
        null -> Log.d(TAG, "latlong is null")
        else -> Log.d(TAG, "latlong is (${it[0]}, ${it[1]})")
      }
    }
  }

  fun getDatetime(context: Context, data: Uri): String? {
    return context.contentResolver.openInputStream(data)?.let {
      val exif = ExifInterface(it)
      val datetime = exif.getAttribute(TAG_DATETIME_ORIGINAL)
      it.close()
      datetime
    }.also { Log.d(TAG, "datetime is ${it}") }
  }

  fun isValidMedia(context: Context, media: Media): Boolean =
    (getImageLatLong(context, Uri.parse(media.url)) != null
        && getDatetime(context, Uri.parse(media.url)) != null).also {
      if (!it) {
        Log.d(TAG, "Invalid media: ${media.url}")
      }
    }


  //TODO: NOT working
  fun getLocation(context: Context, data: Uri): String? {
    val mmr = MediaMetadataRetriever()
    val simple = true
    if (simple) {
      mmr.setDataSource(context, data)
    } else {
      val fd: AssetFileDescriptor?
      try {
        fd = context.contentResolver.openAssetFileDescriptor(data, "r")
      } catch (e: FileNotFoundException) {
        throw IllegalArgumentException()
      }
      requireNotNull(fd)
      val descriptor = fd.getFileDescriptor()
      require(descriptor.valid())
      val startOffset = fd.getStartOffset()
      val declaredLength = fd.getDeclaredLength()
      mmr.setDataSource(descriptor, startOffset, declaredLength);
    }
    val location = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_LOCATION)
    mmr.release()
    return location
  }

  fun getRandomCalendar(media: Media): Calendar = Calendar.getInstance().apply {
    set(
      Random().nextInt(2019), Random().nextInt(12),
      Random().nextInt(28), 0, 0, 0
    )
  }

  fun getRandomLatitude(media: Media): Double = Random().nextInt(180).run { this - 90.0 }
  fun getRandomLongitude(media: Media): Double = Random().nextInt(360).run { this - 180.0 }

}
