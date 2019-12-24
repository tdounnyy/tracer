package duan.felix.tracer.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Spot(
  val calendar: Calendar,
  val latitude: Double,
  val longitude: Double
) : Parcelable {
  override fun toString(): String = StringBuilder("Spot:")
    .apply {
      append("[")
      append(" (${latitude}, ${longitude})")
      append(" @${calendar.time}")
      append("]")
    }
    .toString()

  constructor(parcel: Parcel) : this(
    Calendar.getInstance().apply { timeInMillis = parcel.readLong() },
    parcel.readDouble(),
    parcel.readDouble()
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeLong(calendar.timeInMillis)
    parcel.writeDouble(latitude)
    parcel.writeDouble(longitude)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Spot> {
    override fun createFromParcel(parcel: Parcel): Spot {
      return Spot(parcel)
    }

    override fun newArray(size: Int): Array<Spot?> {
      return arrayOfNulls(size)
    }
  }
}
