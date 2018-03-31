package abhishekdewan101.com.moviedate.ui

import abhishekdewan101.com.moviedate.R
import abhishekdewan101.com.moviedate.appcore.managers.FirebaseAuthenticationManager
import abhishekdewan101.com.moviedate.ui.base.BaseActivity
import abhishekdewan101.com.moviedate.utils.RxLog
import abhishekdewan101.com.moviedate.utils.threads.AppExecutors
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (FirebaseAuth.getInstance().currentUser != null) {
            FirebaseAuth.getInstance().currentUser?.phoneNumber?.let { RxLog.d(TAG, it) }
        } else {
            val manager = FirebaseAuthenticationManager()
            manager.createPhoneAuthorizationCredentials(PhoneAuthProvider.getInstance(),"+16505576463")
                    .flatMap { t: PhoneAuthCredential ->  manager.signInWithPhoneAuthCredentials(t, FirebaseAuth.getInstance())}
                    .subscribeOn(Schedulers.from(AppExecutors.mNetworkIOThread))
                    .subscribe({
                        RxLog.d(TAG,it.displayName.toString())
                    },{
                        RxLog.e(TAG,it.localizedMessage)
                    })
        }


    }
}
