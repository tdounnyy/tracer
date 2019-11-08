package duan.felix.tracer.entity

class Trace {

  private val spots = mutableListOf<Spot>()

  fun add(spot: Spot): Boolean = spots.add(spot)

  fun addAll(c: List<Spot>): Boolean = spots.addAll(c)

  fun getSpotCount(): Int = spots.size

  fun sort() {
    spots.sortWith(compareBy { it.calendar })
  }

  fun getSpot(i: Int): Spot? = if (spots.isEmpty()) {
    null
  } else {
    spots[i]
  }

  override fun toString(): String = StringBuilder("Trace:\n")
    .apply {
      spots.forEachIndexed { index, spot ->
        this.append("#$index ").append("$spot").append("\n")
      }
    }.toString()

}