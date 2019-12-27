package duan.felix.tracer.presenter

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import duan.felix.tracer.R
import duan.felix.tracer.entity.Trace

class TracePresenter(private val context: Context, private val map: GoogleMap) {

  fun drawTrace(trace: Trace) {
    map.addPolyline(PolylineOptions().apply {
      trace.getAllSpot().forEach {
        add(LatLng(it.latitude, it.longitude))
        color(context.resources.getColor(R.color.colorPrimary))
      }
    })
  }
}