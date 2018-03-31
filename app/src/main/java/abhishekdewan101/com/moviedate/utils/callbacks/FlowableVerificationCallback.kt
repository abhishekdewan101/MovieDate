package abhishekdewan101.com.moviedate.utils.callbacks

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import io.reactivex.FlowableEmitter

/**
 * Created by abhishekdewan on 3/31/18.
 */
class FlowableVerificationCallback(emitter: FlowableEmitter<PhoneAuthCredential>) : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    val mEmitter: FlowableEmitter<PhoneAuthCredential> = emitter


    override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
        mEmitter.onNext(p0!!)
    }

    override fun onVerificationFailed(p0: FirebaseException?) {
        mEmitter.onError(Throwable(p0))
    }

}