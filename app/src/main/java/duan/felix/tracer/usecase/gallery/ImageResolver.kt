package duan.felix.tracer.usecase.gallery

import android.content.Context
import android.provider.MediaStore
import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.MediaType

class ImageResolver {

  fun getImages(context: Context): List<Media> {
    val images = mutableListOf<Media>()

    val projection = arrayOf(
      MediaStore.Images.ImageColumns.DATA,
      MediaStore.Images.ImageColumns.LATITUDE,
      MediaStore.Images.ImageColumns.LONGITUDE,
      MediaStore.Images.ImageColumns.TITLE
    )
    val selection =
      "${MediaStore.Images.ImageColumns.DATA} LIKE %?% AND ${MediaStore.Images.ImageColumns.LATITUDE} NOT NULL"
    val args = arrayOf("DCIM/Camera")
    val order = "${MediaStore.Images.ImageColumns.DATE_ADDED} DESC"

    context.contentResolver.query(
      MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
      projection,
      selection,
      args,
      order
    )?.run {
      while (moveToNext()) {
        val image = Media(
          getString(0),
          MediaType.Image,
          getDouble(1),
          getDouble(2),
          getString(3)
        )
        images.add(image)
      }
      close()
    }
    return images
  }

}