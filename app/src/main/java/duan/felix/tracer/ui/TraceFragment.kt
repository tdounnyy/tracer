package duan.felix.tracer.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import duan.felix.tracer.entity.Trace
import duan.felix.tracer.presentation.ISpotPresenter
import duan.felix.tracer.presentation.SpotClusterPresenter

class TraceFragment : SupportMapFragment(), OnMapReadyCallback {
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
    }
  }

  private fun showSpots(map: GoogleMap) {
    if (spotPresenter == null) {
      spotPresenter = SpotClusterPresenter(context!!, map)
      // presenter = SpotSimplePresenter(map)
    }
    spotPresenter.run {
      trace?.getAllSpot()?.let { this?.addSpots(it) }
    }
  }

  companion object {
    const val TAG = "TraceFragment"
    const val ARG_TRACE = "trace"
  }
}