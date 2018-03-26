package abhishekdewan101.com.moviedate.ui

import abhishekdewan101.com.moviedate.R
import abhishekdewan101.com.moviedate.appcore.managers.FirebaseAuthenticationManager
import abhishekdewan101.com.moviedate.utils.RxLog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var manager : FirebaseAuthenticationManager = FirebaseAuthenticationManager()
        manager.signInUserWithEmailAndPassword(FirebaseAuth.getInstance(),"a@b.com","123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    RxLog.d("MovieData~",it.email!!)
                },{
                    RxLog.e("MovieDate~",it.message!!)
                },{

                })
    }
}
