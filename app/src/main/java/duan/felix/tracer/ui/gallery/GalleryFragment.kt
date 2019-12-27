package duan.felix.tracer.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import duan.felix.tracer.R
import duan.felix.tracer.usecase.gallery.ImageResolver

// TODO: make gridview for images
// TODO: load images into gridview
// TODO: make selectable
// TODO: make drag selectable
// TODO: implement MediaPicker
// TODO: make loading view
class GalleryFragment : Fragment() {

  private lateinit var gallery: RecyclerView
  private lateinit var adapter: GalleryAdapter
  private val imageResolver = ImageResolver()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_gallery, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    gallery = view.findViewById(R.id.gallery)
    gallery.layoutManager = GridLayoutManager(view.context, 2)
    adapter = GalleryAdapter(view.context)
    gallery.adapter = adapter
  }

  override fun onResume() {
    super.onResume()
    refresh()
  }

  private fun refresh() {
    adapter.images = imageResolver.getImages(context!!)
  }
}