package duan.felix.tracer.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import duan.felix.tracer.entity.Trace
import duan.felix.tracer.presentation.ISpotPresenter
import duan.felix.tracer.presentation.SpotClusterPresenter
import duan.felix.tracer.presentation.TracePresenter
import util.Consts

class TraceFragment : SupportMapFragment(), OnMapReadyCallback {
  private var tracePresenter: TracePresenter? = null
  private var spotPresenter: ISpotPresenter? = null
  private var trace: Trace? = null
  private var mMap: GoogleMap? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    trace = arguments?.getParcelable(ARG_TRACE)
    Log.d(TAG, "onCreate ${trace}")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getMapAsync(this)
  }

  override fun onMapReady(map: GoogleMap?) {
    map?.let {
      mMap = map
      showSpots(map)
      showLines(map)
      val builder = LatLngBounds.Builder()
      trace?.getAllSpot()?.forEach {
        builder.include(LatLng(it.latitude, it.longitude))
      }
      map.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), Consts.CAMERA_PADDING))
    }
  }

  private fun showSpots(map: GoogleMap) {
    if (spotPresenter == null) {
      spotPresenter = SpotClusterPresenter(context!!, map)
      // presenter = SpotSimplePresenter(map)
    }
    spotPresenter?.run {
      trace?.getAllSpot()?.let { this.addSpots(it) }
    }
  }

  private fun showLines(map: GoogleMap) {
    if (tracePresenter == null) {
      tracePresenter = TracePresenter(context!!, map)
    }
    tracePresenter?.run {
      trace?.let {
        drawTrace(it)
      }
    }
  }

  companion object {
    const val TAG = "TraceFragment"
    const val ARG_TRACE = "trace"
  }
}