package duan.felix.tracer.presenter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import duan.felix.tracer.R
import duan.felix.tracer.app.TracerApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SpotThumbnailRenderer(
  context: Context,
  map: GoogleMap,
  manager: ClusterManager<SpotClusterItem>
) : DefaultClusterRenderer<SpotClusterItem>(context, map, manager) {

  private val mIconGenerator = IconGenerator(context.applicationContext)
  private val mClusterIconGenerator = IconGenerator(context.applicationContext)
  private val mImageView: ImageView
  private val mClusterImageView: ImageView
  private val mClusterCounter: TextView
  private val layoutInflater = LayoutInflater.from(context)

  init {
    val thumbnail = layoutInflater.inflate(R.layout.thumbnail_spot, null)
    mImageView = thumbnail.findViewById(R.id.icon)
    mIconGenerator.setContentView(thumbnail)

    val clusterThumbnail = layoutInflater.inflate(R.layout.thumbnail_spot_cluster, null)
    mClusterImageView = clusterThumbnail.findViewById(R.id.icon)
    mClusterCounter = clusterThumbnail.findViewById(R.id.count)
    mClusterIconGenerator.setContentView(clusterThumbnail)
  }

  override fun onClusterItemRendered(clusterItem: SpotClusterItem?, marker: Marker?) {
    GlobalScope.launch {
      Glide.with(TracerApp.appContext).asBitmap().load(clusterItem?.spot?.url)
        .addListener(object : RequestListener<Bitmap> {
          override fun onResourceReady(
            resource: Bitmap?,
            model: Any?,
            target: Target<Bitmap>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
          ): Boolean {
            GlobalScope.launch(Dispatchers.Main) {
              mImageView.setImageBitmap(resource!!)
              val bmp = mIconGenerator.makeIcon()
              marker?.setIcon(BitmapDescriptorFactory.fromBitmap(bmp))
            }
            return true
          }

          override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Bitmap>?,
            isFirstResource: Boolean
          ): Boolean {
            TODO("not implemented")
          }
        }).submit(BITMAP_SIZE, BITMAP_SIZE)
    }
  }

  override fun onClusterRendered(cluster: Cluster<SpotClusterItem>?, marker: Marker?) {
    GlobalScope.launch {
      Glide.with(TracerApp.appContext).asBitmap().load(cluster?.items?.first()?.spot?.url)
        .addListener(object : RequestListener<Bitmap> {
          override fun onResourceReady(
            resource: Bitmap?,
            model: Any?,
            target: Target<Bitmap>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
          ): Boolean {
            GlobalScope.launch(Dispatchers.Main) {
              // TODO: show better text. Color, 99+, etc.
              mClusterCounter.setText(cluster?.size.toString())
              mClusterImageView.setImageBitmap(resource!!)
              val bmp = mClusterIconGenerator.makeIcon()
              marker?.setIcon(BitmapDescriptorFactory.fromBitmap(bmp))
            }
            return true
          }

          override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Bitmap>?,
            isFirstResource: Boolean
          ): Boolean {
            TODO("not implemented")
          }
        }).submit(BITMAP_SIZE, BITMAP_SIZE)
    }
  }

  companion object {
    const val BITMAP_SIZE = 100
  }
}
