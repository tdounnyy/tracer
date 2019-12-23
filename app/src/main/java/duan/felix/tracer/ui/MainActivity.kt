package duan.felix.tracer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import duan.felix.tracer.R
import util.PermissionUtils

/**
 * @author duanyufei@dayuwuxian.com at 2019/11/13
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    PermissionUtils.assurePermissions(this)
  }
}