package duan.felix.tracer.gallery.presenter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import duan.felix.tracer.gallery.ui.GalleryAdapter
import duan.felix.tracer.usecase.gallery.ImageResolver

class GalleryPresenter(private val context: Context, gallery: RecyclerView) {

  private val adapter = GalleryAdapter(context)
  private val imageResolver = ImageResolver()

  init {
    gallery.layoutManager = GridLayoutManager(context, 2)
    gallery.adapter = adapter
  }

  fun refresh() {
    adapter.images = imageResolver.fetchImages(context)
  }
}