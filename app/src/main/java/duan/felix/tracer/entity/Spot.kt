package duan.felix.tracer.entity

import android.os.Parcel
import android.os.Parcelable
import com.bumptech.glide.Glide
import duan.felix.tracer.app.TracerApp
import java.util.*

data class Spot(
  val calendar: Calendar,
  val latitude: Double,
  val longitude: Double,
  val url: String
) : Parcelable {

  fun preload() {
    Glide.with(TracerApp.appContext).load(url).preload()
  }

  override fun toString(): String = StringBuilder("Spot:")
    .apply {
      append("[")
      append(" (${latitude}, ${longitude})")
      append(" @${calendar.time}")
      append(" =${url}")
      append("]")
    }
    .toString()

  constructor(parcel: Parcel) : this(
    Calendar.getInstance().apply { timeInMillis = parcel.readLong() },
    parcel.readDouble(),
    parcel.readDouble(),
    parcel.readString()!!
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeLong(calendar.timeInMillis)
    parcel.writeDouble(latitude)
    parcel.writeDouble(longitude)
    parcel.writeString(url)
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
