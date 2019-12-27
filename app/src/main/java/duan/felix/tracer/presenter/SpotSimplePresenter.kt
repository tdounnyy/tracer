package duan.felix.tracer.presenter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import duan.felix.tracer.entity.Spot

class SpotSimplePresenter(val map: GoogleMap) : ISpotPresenter {

  override fun addSpots(spots: List<Spot>) {
    spots.forEach {
      showMark(map, it.latitude, it.longitude)
      focusCamera(map, it.latitude, it.longitude)
    }
  }

  // Without cluster manager
  private fun showMark(map: GoogleMap, lat: Double, long: Double) {
    val mark = LatLng(lat, long)
    map.addMarker(
      MarkerOptions().position(mark)
        .title("Marker in Somewhere")
    )
  }

  private fun focusCamera(map: GoogleMap, lat: Double, long: Double) {
    map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(lat, long)))
  }
}