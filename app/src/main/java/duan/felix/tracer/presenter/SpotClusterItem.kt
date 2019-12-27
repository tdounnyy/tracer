package duan.felix.tracer.presenter

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import duan.felix.tracer.entity.Spot

class SpotClusterItem(val spot: Spot) : ClusterItem {

  private val position: LatLng = LatLng(spot.latitude, spot.longitude)

  override fun getSnippet(): String = "Snippet"

  override fun getTitle(): String = "title"

  override fun getPosition(): LatLng = position

}