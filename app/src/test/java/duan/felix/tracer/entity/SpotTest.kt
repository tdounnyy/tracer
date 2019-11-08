package duan.felix.tracer.entity

import android.location.Location
import org.junit.Test
import java.util.*

class SpotTest {

  var location = Location("")
  val spot = Spot(Calendar.getInstance(), Location("fake"))

  @Test
  fun testToString() {
    TODO("fail, should make platform inrelative")
    this.location.latitude = 100.0
    this.location.longitude = 50.0
    print(spot)
  }

  @Test
  fun getCalendar() {
  }

  @Test
  fun getLocation() {
  }
}