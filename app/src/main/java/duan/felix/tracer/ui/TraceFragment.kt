package duan.felix.tracer.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import duan.felix.tracer.entity.Trace

class TraceFragment : SupportMapFragment(), OnMapReadyCallback {
  private var trace: Trace? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    trace = arguments?.getParcelable<Trace>(ARG_TRACE)
    Log.d(TAG, "onCreate ${trace}")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getMapAsync(this)
  }

  override fun onMapReady(map: GoogleMap?) {
    map?.let {
      trace?.getAllSpot()?.forEach {
        showMark(map, it.latitude, it.longitude)
        focusCamera(map, it.latitude, it.longitude)
      }
    }

  }

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

  companion object {
    const val TAG = "TraceFragment"
    const val ARG_TRACE = "trace"
  }
}