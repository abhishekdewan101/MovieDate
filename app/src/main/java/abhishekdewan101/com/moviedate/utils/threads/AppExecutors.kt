package abhishekdewan101.com.moviedate.utils.threads

import java.util.concurrent.Executors

/**
 * Created by abhishekdewan on 3/31/18.
 */
object AppExecutors {
    val mMainThead = Executors.newFixedThreadPool(1)
    val mNetworkIOThread = Executors.newFixedThreadPool(5)
    val mPhoneAuthThread = Executors.newFixedThreadPool(1)
}
