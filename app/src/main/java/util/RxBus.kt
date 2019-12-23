package util

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {
  val subject = PublishSubject.create<Event>().toSerialized()

  fun observe(idArray: IntArray): Observable<Event> {
    return subject.filter { idArray.contains(it.id) }
  }

  fun publish(id: Int, obj: Any) {
    subject.onNext(Event(id, obj))
  }
}

// TODO: 2019/12/24 (duanyufei) make better Event
class Event(val id: Int, val obj: Any) {
  companion object {
    const val TRACE = 0
  }
}
