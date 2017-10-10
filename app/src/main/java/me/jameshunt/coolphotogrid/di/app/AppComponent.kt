package me.jameshunt.coolphotogrid.di.app

import dagger.Component
import me.jameshunt.coolphotogrid.feature.activity.ActivityContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.UnsplashService
import javax.inject.Singleton

/**
 * Created by James on 10/4/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

    fun getNewPhotosRxObserver(): RxCommunicatorContract.Observer<RxNewPhotos>
    fun getNewPhotosRxEmitter(): RxCommunicatorContract.Emitter<RxNewPhotos>

    fun getAlbumClickedRxObserver(): RxCommunicatorContract.Observer<RxAlbumData>
    fun getAlbumClickedRxEmitter(): RxCommunicatorContract.Emitter<RxAlbumData>


    fun getUnsplashService(): UnsplashService

    fun getActivityModel(): ActivityContract.Model

}