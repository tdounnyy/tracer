package duan.felix.tracer.gallery.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import duan.felix.tracer.R
import duan.felix.tracer.entity.Media
import kotlinx.android.synthetic.main.widget_gallery_item.view.*

class GalleryAdapter(context: Context) : RecyclerView.Adapter<GalleryItemHolder>() {

  var images: List<Media>? = null
    set(value) {
      field = value
      selection.clear()
      notifyDataSetChanged()
    }

  private var selection = GallerySelection()

  private val inflater: LayoutInflater = LayoutInflater.from(context)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemHolder {
    val view = inflater.inflate(R.layout.widget_gallery_item, parent, false)
    return GalleryItemHolder(view, selection)
  }

  override fun getItemCount(): Int {
    return images?.size ?: 0
  }

  override fun onBindViewHolder(holder: GalleryItemHolder, position: Int) {
    images?.let {
      holder.bind(it[position], position)
    }
  }

  fun getSelection(): MutableList<Media> {
    val list = mutableListOf<Media>()
    images?.let {
      selection.getAllSelection().forEach { index -> list.add(it[index]) }
    }
    return list
  }
}

class GallerySelection() {
  private val selected = mutableListOf<Int>()

  fun toggleSelection(view: View, index: Int) {
    if (selected.contains(index)) {
      selected.remove(index)
      // TODO: cast is BAD
      (view as CardView).setCardBackgroundColor(view.context.resources.getColor(android.R.color.transparent))
    } else {
      selected.add(index)
      (view as CardView).setCardBackgroundColor(view.context.resources.getColor(R.color.colorAccent))
    }
  }

  fun getAllSelection(): List<Int> {
    return selected.toList()
  }

  fun clear() {
    selected.clear()
  }
}

class GalleryItemHolder(
  private val galleryItem: View,
  selection: GallerySelection
) :
  RecyclerView.ViewHolder(galleryItem) {

  private var index: Int = -1
  private val onClickListener = View.OnClickListener {
    Log.d("felixx", "click on index:$index, $this")
    selection.toggleSelection(it, index)
  }

  fun bind(media: Media, position: Int) {
    index = position
    Glide.with(itemView.context).load(media.url)
      .placeholder(android.R.drawable.stat_notify_sync_noanim).into(galleryItem.image)
    galleryItem.title.text = media.title
    itemView.setOnClickListener(onClickListener)
  }
}
