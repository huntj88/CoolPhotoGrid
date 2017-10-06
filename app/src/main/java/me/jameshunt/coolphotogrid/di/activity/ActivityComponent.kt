package me.jameshunt.coolphotogrid.di.activity

import dagger.Component
import me.jameshunt.coolphotogrid.di.app.AppComponent
import me.jameshunt.coolphotogrid.feature.activity.MainActivity
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.api.ApiFactory

/**
 * Created by James on 10/5/2017.
 */

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class, RealmModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    fun getNewPhotosRxObserver(): RxCommunicatorContract.Observer<RxNewPhotos>
    fun getNewPhotosRxEmitter(): RxCommunicatorContract.Emitter<RxNewPhotos>

    fun getApiFactory(): ApiFactory

    fun inject(mainActivity: MainActivity)
}