package duan.felix.tracer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import duan.felix.tracer.R
import duan.felix.tracer.entity.Trace

class TraceFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val trace = arguments?.getParcelable<Trace>(ARG_TRACE)
    Log.d(TAG, "onCreate ${trace}")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_map, container, false)
  }

  companion object {
    const val TAG = "TraceFragment"
    const val ARG_TRACE = "trace"
  }
}