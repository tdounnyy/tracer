package duan.felix.tracer.gallery.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import duan.felix.tracer.R
import duan.felix.tracer.entity.Media
import duan.felix.tracer.entity.Spot
import duan.felix.tracer.gallery.presenter.GalleryPresenter
import duan.felix.tracer.usecase.MediaUtils
import duan.felix.tracer.usecase.SpotBuilder

// TODO: make selectable
// TODO: make drag selectable
// TODO: implement MediaPicker
// TODO: make loading view
class GalleryFragment : Fragment() {

  private lateinit var presenter: GalleryPresenter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    setHasOptionsMenu(true)
    return inflater.inflate(R.layout.fragment_gallery, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(view.findViewById(R.id.gallery) as RecyclerView) {
      presenter = GalleryPresenter(view.context, this)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.gallery_fragment, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.select -> {
        val medias = presenter.finishSelection()
        MediaUtils.sendToTarget(medias)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onResume() {
    super.onResume()
    presenter.refresh()
  }
}