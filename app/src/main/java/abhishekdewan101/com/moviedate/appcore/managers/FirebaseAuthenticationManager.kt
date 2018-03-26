package abhishekdewan101.com.moviedate.appcore.managers

import abhishekdewan101.com.moviedate.utils.RxLog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * Created by abhishekdewan on 3/25/18.
 */

class FirebaseAuthenticationManager {

    fun signInUserWithEmailAndPassword(auth:FirebaseAuth,email:String,password:String) : Flowable<FirebaseUser> {
        return Flowable.create({ emitter ->
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener({
                if (it.isSuccessful) {
                    RxLog.d("MovieDate~","Login was successful")
                    emitter.onNext(auth.currentUser!!)
                } else {
                    RxLog.d("MovieDate~","Login was not successful")
                    emitter.onError(Throwable("User Could not Login"))
                }
            })
        },BackpressureStrategy.LATEST)
    }

}