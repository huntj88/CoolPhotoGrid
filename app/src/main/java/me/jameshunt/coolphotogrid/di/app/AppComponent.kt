package me.jameshunt.coolphotogrid.di.app

import dagger.Component
import me.jameshunt.coolphotogrid.feature.activity.ActivityContract
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import javax.inject.Singleton

/**
 * Created by James on 10/4/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, RealmModule::class))
interface AppComponent {

    fun getAlbumClickedRxObserver(): RxCommunicatorContract.Observer<RxAlbumData>
    fun getAlbumClickedRxEmitter(): RxCommunicatorContract.Emitter<RxAlbumData>

    fun getRealmInstanceManager(): RealmInstanceManager
    fun getModelHolder(): ModelHolder


    fun getUnsplashService(): UnsplashService

    fun getActivityModel(): ActivityContract.Model

}