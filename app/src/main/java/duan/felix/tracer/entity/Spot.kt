package duan.felix.tracer.entity

import android.location.Location
import java.util.*

class Spot(val calendar: Calendar,
           val location: Location) {
  override fun toString(): String = StringBuilder("Spot:")
    .apply {
      append("[")
      append(" (${location.longitude}, ${location.latitude})")
      append(" @${calendar}")
      append("]")
    }
    .toString()
}
