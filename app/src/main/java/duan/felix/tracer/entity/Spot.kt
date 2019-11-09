package duan.felix.tracer.entity

import java.util.*

data class Spot(val calendar: Calendar,
                val latitude: Double,
                val longitude: Double
) {
  override fun toString(): String = StringBuilder("Spot:")
    .apply {
      append("[")
      append(" (${latitude}, ${longitude})")
      append(" @${calendar.time}")
      append("]")
    }
    .toString()
}
