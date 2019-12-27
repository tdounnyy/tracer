package duan.felix.tracer.gallery.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import duan.felix.tracer.R
import duan.felix.tracer.entity.Media
import kotlinx.android.synthetic.main.widget_gallery_item.view.*

class GalleryAdapter(context: Context) : RecyclerView.Adapter<GalleryItemHolder>() {

  var images: List<Media>? = null
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  private val inflater: LayoutInflater = LayoutInflater.from(context)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemHolder {
    val view = inflater.inflate(R.layout.widget_gallery_item, parent, false)
    return GalleryItemHolder(view)
  }

  override fun getItemCount(): Int {
    return images?.size ?: 0
  }

  override fun onBindViewHolder(holder: GalleryItemHolder, position: Int) {
    images?.let {
      holder.bind(it[position])
    }
  }
}

class GalleryItemHolder(private val galleryItem: View) :
  RecyclerView.ViewHolder(galleryItem) {
  fun bind(media: Media) {
    Glide.with(itemView.context).load(media.url)
      .placeholder(android.R.drawable.stat_notify_sync_noanim).into(galleryItem.image)
    galleryItem.title.text = media.title
  }
}
