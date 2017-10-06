package me.jameshunt.coolphotogrid.di.app

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ActivityContract
import me.jameshunt.coolphotogrid.feature.activity.ActivityModel
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicator
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import javax.inject.Singleton

/**
 * Created by James on 10/5/2017.
 */
@Module
class AppModule {


    @Singleton
    @Provides
    fun getActivityModel(initialDataEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>): ActivityContract.Model {
        return ActivityModel(initialDataEmitter)
    }


    @Singleton
    @Provides
    fun getNewPhotosRxCommunicator(): RxCommunicator<RxNewPhotos> {
        return RxCommunicator()
    }

    @Provides
    fun getNewPhotosRxObserver(rxCommunicator: RxCommunicator<RxNewPhotos>): RxCommunicatorContract.Observer<RxNewPhotos> {
        return rxCommunicator
    }

    @Provides
    fun getNewPhotosRxEmitter(rxCommunicator: RxCommunicator<RxNewPhotos>): RxCommunicatorContract.Emitter<RxNewPhotos> {
        return rxCommunicator
    }

}