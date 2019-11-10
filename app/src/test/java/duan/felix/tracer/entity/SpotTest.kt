package duan.felix.tracer.entity

import com.google.common.truth.Truth.assertThat
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
    assertThat(spot.calendar).isEqualTo(calendar)
  }

  @Test
  fun getLongitude() {
    assertThat(spot.longitude).isEqualTo(90.0)
  }

  @Test
  fun getLatitude() {
    assertThat(spot.latitude).isEqualTo(50.0)
  }
}