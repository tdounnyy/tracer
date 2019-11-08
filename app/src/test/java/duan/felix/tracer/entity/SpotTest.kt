package duan.felix.tracer.entity

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*

class SpotTest {

  val calendar = Calendar.getInstance()
  val spot = Spot(calendar, 50.0, 90.0)

  @Test
  fun testToString() {
    print(spot)
  }

  @Test
  fun getCalendar() {
    assertThat(spot.calendar, equalTo(calendar))
  }

  @Test
  fun getLongitude() {
    assertThat(spot.longitude, equalTo(50.0))
  }

  @Test
  fun getLatitude() {
    assertThat(spot.latitude, equalTo(90.0))
  }
}