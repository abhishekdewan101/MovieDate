package abhishekdewan101.com.moviedate.utils

import android.util.Log

/**
 * Created by abhishekdewan on 3/25/18.
 */

class RxLog {
    companion object {
        fun d(tag:String,message:String) {
            val formattedMessage = message + " - " + Thread.currentThread().name + " - " + System.currentTimeMillis()
            Log.d(tag,formattedMessage)
        }

        fun e(tag:String,message:String) {
            val formattedMessage = message + " - " + Thread.currentThread().name + " - " + System.currentTimeMillis()
            Log.e(tag,formattedMessage)
        }
    }
}