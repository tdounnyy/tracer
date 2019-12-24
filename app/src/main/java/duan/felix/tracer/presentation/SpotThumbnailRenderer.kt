package duan.felix.tracer.presentation

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import duan.felix.tracer.R


class SpotThumbnailRenderer(
  context: Context,
  map: GoogleMap,
  manager: ClusterManager<SpotClusterItem>
) : DefaultClusterRenderer<SpotClusterItem>(context, map, manager) {

  private val mIconGenerator = IconGenerator(context.applicationContext)
  private val mClusterIconGenerator = IconGenerator(context.applicationContext)
  private val mImageView: ImageView
  private val mClusterImageView: ImageView
  private val layoutInflater = LayoutInflater.from(context)

  init {
    var thumbnail = layoutInflater.inflate(R.layout.thumbnail_spot, null)
    mImageView = thumbnail.findViewById(R.id.icon)
    mIconGenerator.setContentView(thumbnail)

    thumbnail = layoutInflater.inflate(R.layout.thumbnail_spot_cluster, null)
    mClusterImageView = thumbnail.findViewById(R.id.icon)
    mClusterIconGenerator.setContentView(thumbnail)
  }

  override fun onBeforeClusterItemRendered(item: SpotClusterItem?, markerOptions: MarkerOptions?) {
    val icon = mIconGenerator.makeIcon()
    markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(icon))?.title("Icon")
  }

  override fun onBeforeClusterRendered(
    cluster: Cluster<SpotClusterItem>?,
    markerOptions: MarkerOptions?
  ) {
    val icon = mClusterIconGenerator.makeIcon()
    markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(icon))?.title("Cluster")
  }
}
