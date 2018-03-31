package abhishekdewan101.com.moviedate.appcore.managers

import abhishekdewan101.com.moviedate.utils.Config
import abhishekdewan101.com.moviedate.utils.RxLog
import abhishekdewan101.com.moviedate.utils.callbacks.FlowableVerificationCallback
import abhishekdewan101.com.moviedate.utils.threads.AppExecutors
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by abhishekdewan on 3/25/18.
 */

class FirebaseAuthenticationManager : BaseManager() {

    fun signInUserWithEmailAndPassword(auth:FirebaseAuth,email:String,password:String) : Flowable<FirebaseUser> {
        RxLog.d(TAG,"signInUserWithEmailAndPassword")
        return Flowable.create({ emitter ->
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener({
                if (it.isSuccessful) {
                    RxLog.d(TAG,"Login was successful")
                    emitter.onNext(auth.currentUser!!)
                } else {
                    RxLog.d(TAG,"Login was not successful")
                    emitter.onError(Throwable(it.exception))
                }
            })
        },BackpressureStrategy.LATEST)
    }

    fun createUserWithEmailAndPassword(auth:FirebaseAuth,email:String,password:String) : Flowable<FirebaseUser> {
        RxLog.d(TAG,"createUserWithEmailAndPassword")
        return Flowable.create({emitter ->
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener({
                if (it.isSuccessful) {
                    RxLog.d(TAG,"User was sucessfully created")
                    emitter.onNext(auth.currentUser!!)
                } else {
                    RxLog.d(TAG,"User was not successfully created")
                    emitter.onError(Throwable(it.exception))
                }
            })
        },BackpressureStrategy.LATEST)
    }

    fun createPhoneAuthorizationCredentials(authProvider: PhoneAuthProvider,phoneNumber:String) : Flowable<PhoneAuthCredential> {
        RxLog.d(TAG,"createPhoneAuthorizationCredentials")
        return Flowable.create({emitter ->
           authProvider.verifyPhoneNumber(
                   phoneNumber,
                   1,
                   TimeUnit.MINUTES,
                   AppExecutors.mMainThead,
                   FlowableVerificationCallback(emitter)
           )
        },BackpressureStrategy.LATEST)
    }

    fun signInWithPhoneAuthCredentials(credential: PhoneAuthCredential,auth: FirebaseAuth) : Flowable<FirebaseUser> {
        RxLog.d(TAG,"signInWithPhoneAuthCredentials")
        return Flowable.create({emitter ->
            auth.signInWithCredential(credential).addOnCompleteListener({
                if (it.isSuccessful) {
                    RxLog.d(TAG,"User was signed in")
                    emitter.onNext(auth.currentUser!!)
                } else {
                    RxLog.d(TAG,"User was not signed in")
                    emitter.onError(Throwable(it.exception))
                }
            })
        },BackpressureStrategy.LATEST)
    }

}