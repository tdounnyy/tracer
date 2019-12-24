package duan.felix.tracer.entity

import android.os.Parcel
import android.os.Parcelable

class Trace() : Parcelable {

  private val spots = mutableListOf<Spot>()

  fun add(spot: Spot): Boolean = spots.add(spot)

  fun addAll(spots: List<Spot>): Boolean = this.spots.addAll(spots)

  fun getSpotCount(): Int = spots.size

  fun isEmpty(): Boolean = spots.isEmpty()

  fun sort() {
    spots.sortWith(compareBy { it.calendar })
  }

  fun getSpot(i: Int): Spot? = if (spots.isEmpty()) {
    null
  } else {
    spots[i]
  }

  fun getAllSpot(): MutableList<Spot> {
    return spots
  }

  override fun toString(): String = StringBuilder("Trace:\n")
    .apply {
      spots.forEachIndexed { index, spot ->
        this.append("#$index ").append("$spot").append("\n")
      }
    }.toString()

  constructor(parcel: Parcel) : this() {
    val size = parcel.dataSize()
    val loader = Trace::class.java.classLoader
    for (i in 0..size) {
      spots.add(i, parcel.readParcelable(loader)!!)
    }
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    spots.forEach { parcel.writeParcelable(it, flags) }
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Trace> {
    override fun createFromParcel(parcel: Parcel): Trace {
      return Trace(parcel)
    }

    override fun newArray(size: Int): Array<Trace?> {
      return arrayOfNulls(size)
    }
  }

}