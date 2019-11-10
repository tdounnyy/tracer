package duan.felix.tracer.entity

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/9
 */
class MemMediaRepoTest {

  val media1 = Media("file://media1", MediaType.Image)
  val media2 = Media("file://media2", MediaType.Image)
  val media3 = Media("file://media3", MediaType.Image)

  @Before
  fun setup() {
    MemMediaRepo.clear()
  }

  @Test
  fun getSize() {
    assertThat(MemMediaRepo.getSize()).isEqualTo(0)
    MemMediaRepo.addMedia(media1)
    assertThat(MemMediaRepo.getSize()).isEqualTo(1)
  }

  @Test
  fun getAll() {
    MemMediaRepo.addMedia(media1)
    MemMediaRepo.addMedia(media2)
    MemMediaRepo.addMedia(media1)
    val all = MemMediaRepo.getAll()
    assertThat(all.size).isEqualTo(2)
    assertThat(all[0]).isEqualTo(media1)
    assertThat(all[1]).isEqualTo(media2)
  }

  @Test
  fun addMedia() {
    MemMediaRepo.addMedia(media1)
    assertThat(MemMediaRepo.getSize()).isEqualTo(1)
    MemMediaRepo.addMedia(media1)
    assertThat(MemMediaRepo.getSize()).isEqualTo(1)
  }

  @Test
  fun getMedia() {
    MemMediaRepo.addMedia(media1)
    MemMediaRepo.addMedia(media2)
    val media = MemMediaRepo.getMedia(media1.url)
    assertThat(media).isEqualTo(media1)
  }

  @Test
  fun getMediaList() {
    MemMediaRepo.addMedia(media1)
    MemMediaRepo.addMedia(media2)
    MemMediaRepo.addMedia(media3)
    val result = MemMediaRepo.getMediaList(listOf(media1.url, media2.url))
    assertThat(result.size).isEqualTo(2)
    assertThat(result).containsExactly(media1, media2)
    assertThat(result).doesNotContain(media3)
  }

  @Test
  fun clear() {
    MemMediaRepo.addMedia(media1)
    MemMediaRepo.addMedia(media2)
    MemMediaRepo.addMedia(media3)
    assertThat(MemMediaRepo.getSize()).isNotEqualTo(0)
    MemMediaRepo.clear()
    assertThat(MemMediaRepo.getSize()).isEqualTo(0)
  }
}
