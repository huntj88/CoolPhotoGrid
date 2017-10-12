package me.jameshunt.coolphotogrid.di.activity

import dagger.Component
import me.jameshunt.coolphotogrid.di.app.AppComponent
import me.jameshunt.coolphotogrid.feature.activity.MainActivity
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.api.album.SelectAlbumApiFactory
import me.jameshunt.coolphotogrid.repo.api.photo.PhotoApiFactory

/**
 * Created by James on 10/5/2017.
 */

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    fun getModelHolder(): ModelHolder

    fun getAlbumClickedRxObserver(): RxCommunicatorContract.Observer<RxAlbumData>
    fun getAlbumClickedRxEmitter(): RxCommunicatorContract.Emitter<RxAlbumData>

    fun getPhotoApiFactory(): PhotoApiFactory
    fun getSelectAlbumApiFactory(): SelectAlbumApiFactory

    fun inject(mainActivity: MainActivity)
}