package duan.felix.tracer.entity

class Media(val url: String,
            val mediaType: MediaType) {
  override fun toString(): String = "Media: url=$url, type=$mediaType"
}
