package duan.felix.tracer.presenter

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import duan.felix.tracer.entity.Spot

class SpotClusterPresenter(context: Context, map: GoogleMap) : ISpotPresenter {

  private var mClusterItemManager: ClusterManager<SpotClusterItem>? = null

  init {
    mClusterItemManager = ClusterManager<SpotClusterItem>(context, map).apply {
      renderer = SpotThumbnailRenderer(context, map, this)
    }
    map.setOnCameraIdleListener(mClusterItemManager)
    map.setOnMarkerClickListener(mClusterItemManager)
  }

  override fun addSpots(spots: List<Spot>) {
    spots.forEach {
      val item = SpotClusterItem(it)
      mClusterItemManager?.addItem(item)
    }
  }
}

