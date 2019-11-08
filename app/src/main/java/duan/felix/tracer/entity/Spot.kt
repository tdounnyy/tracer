package duan.felix.tracer.entity

import java.util.*

class Spot(
  val calendar: Calendar,
  val longitude: Double,
  val latitude: Double
) {
  override fun toString(): String = StringBuilder("Spot:")
    .apply {
      append("[")
      append(" (${longitude}, ${latitude})")
      append(" @${calendar.time}")
      append("]")
    }
    .toString()
}
