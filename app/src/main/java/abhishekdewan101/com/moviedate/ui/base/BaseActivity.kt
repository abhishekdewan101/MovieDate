package abhishekdewan101.com.moviedate.ui.base

import abhishekdewan101.com.moviedate.utils.Config
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by abhishekdewan on 3/31/18.
 */

open class BaseActivity : AppCompatActivity() {
    val TAG = Config.LOG_PREFIX + this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}