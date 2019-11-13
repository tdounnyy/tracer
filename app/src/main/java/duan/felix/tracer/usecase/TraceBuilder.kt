package duan.felix.tracer.usecase

import duan.felix.tracer.entity.Spot
import duan.felix.tracer.entity.Trace

class TraceBuilder {

  fun buildTrace(spots: List<Spot>): Trace? {
    if (spots.isNullOrEmpty()) {
      return null
    }
    return Trace().apply {
      addAll(spots)
      sort()
    }
  }

  companion object {
    const val TAG = "TraceBuilder"
  }
}