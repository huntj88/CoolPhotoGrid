package me.jameshunt.coolphotogrid

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import me.jameshunt.coolphotogrid.di.app.AppComponent
import me.jameshunt.coolphotogrid.di.app.AppModule
import me.jameshunt.coolphotogrid.di.app.DaggerAppComponent
import me.jameshunt.coolphotogrid.di.app.NetworkModule
import timber.log.Timber

/**
 * Created by James on 10/5/2017.
 */
class CoolApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Fresco.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.i("Timber logging started")
        }

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule())
                .networkModule(NetworkModule())
                .build()


        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}