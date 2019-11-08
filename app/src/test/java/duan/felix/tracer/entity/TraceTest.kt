package duan.felix.tracer.entity

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.*

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/9
 */
class TraceTest {

  val trace = Trace()
  val calendar1 = Calendar.Builder().setDate(100, 3, 1).build()
  val calendar2 = Calendar.Builder().setDate(100, 3, 2).build()
  val spot1 = Spot(calendar1, 100.0, 30.0)
  val spot2 = Spot(calendar2, 100.0, 30.0)

  @Test
  fun add() {
    assertThat(trace.getSpotCount(), equalTo(0))
    trace.add(spot1)
    assertThat(trace.getSpotCount(), equalTo(1))
  }

  @Test
  fun addAll() {
    assertThat(trace.getSpotCount(), equalTo(0))
    trace.addAll(listOf(spot1, spot2))
    assertThat(trace.getSpotCount(), equalTo(2))
  }

  @Test
  fun sort1() {
    trace.add(spot1)
    trace.add(spot2)
    trace.sort()
    assertThat(trace.getSpot(0), equalTo(spot1))
    assertThat(trace.getSpot(1), equalTo(spot2))
  }

  @Test
  fun sort2() {
    trace.add(spot2)
    trace.add(spot1)
    trace.sort()
    assertThat(trace.getSpot(0), equalTo(spot1))
    assertThat(trace.getSpot(1), equalTo(spot2))
  }

  @Test
  fun testToString() {
    trace.addAll(listOf(spot1, spot2))
    print(trace)
  }
}