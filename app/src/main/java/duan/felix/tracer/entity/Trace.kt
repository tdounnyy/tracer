package duan.felix.tracer.entity

class Trace {

  private val spots = mutableListOf<Spot>()

  fun add(spot: Spot): Boolean = spots.add(spot)

  fun addAll(c: List<Spot>): Boolean = spots.addAll(c)

  fun sort() {
    TODO("sort spot by calendar")
  }

  override fun toString(): String = StringBuilder("Trace:")
    .apply {
      spots.forEachIndexed { index, spot ->
        this.append("#$index ").append("$spot").append("\n")
      }
    }.toString()

}