package duan.felix.tracer.entity

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/9
 */
interface MediaRepository {

  fun getSize(): Int

  fun getAll(): List<Media>

  fun addMedia(media: Media): Boolean

  fun getMedia(url: String): Media?

  fun getMediaList(urlList: List<String>): List<Media>

  fun clear()
}

object MemMediaRepo : MediaRepository {
  private val list = mutableListOf<Media>()

  override fun getSize(): Int = list.size

  override fun getAll(): List<Media> = list.toList()

  override fun addMedia(media: Media): Boolean = !list.contains(media) && list.add(media)

  override fun getMedia(url: String): Media? {
    list.forEach { if (it.url == url) return it }
    return null
  }

  override fun getMediaList(urlList: List<String>): List<Media> {
    return list.filter { urlList.contains(it.url) }
  }

  override fun clear() = list.clear()
}