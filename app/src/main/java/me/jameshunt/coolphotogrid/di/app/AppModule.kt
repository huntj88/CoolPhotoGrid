package me.jameshunt.coolphotogrid.di.app

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ActivityContract
import me.jameshunt.coolphotogrid.feature.activity.ActivityModel
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicator
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import javax.inject.Singleton

/**
 * Created by James on 10/5/2017.
 */
@Module
class AppModule {


    @Singleton
    @Provides
    fun getActivityModel(initialDataEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>, modelHolder: ModelHolder): ActivityContract.Model {
        return ActivityModel(initialDataEmitter, modelHolder)
    }


    @Singleton
    @Provides
    fun getModelHolder(): ModelHolder {
        return ModelHolder()
    }


    @Singleton
    @Provides
    fun getNewPhotosRxCommunicator(): RxCommunicator<RxNewPhotos> {
        return RxCommunicator()
    }

    @Singleton
    @Provides
    fun getNewPhotosRxObserver(rxCommunicator: RxCommunicator<RxNewPhotos>): RxCommunicatorContract.Observer<RxNewPhotos> {
        return rxCommunicator
    }

    @Singleton
    @Provides
    fun getNewPhotosRxEmitter(rxCommunicator: RxCommunicator<RxNewPhotos>): RxCommunicatorContract.Emitter<RxNewPhotos> {
        return rxCommunicator
    }




    @Singleton
    @Provides
    fun getAlbumClickedCommunicator(): RxCommunicator<RxAlbumData> {
        return RxCommunicator()
    }

    @Singleton
    @Provides
    fun getAlbumClickedRxObserver(rxCommunicator: RxCommunicator<RxAlbumData>): RxCommunicatorContract.Observer<RxAlbumData> {
        return rxCommunicator
    }

    @Singleton
    @Provides
    fun getAlbumClickedRxEmitter(rxCommunicator: RxCommunicator<RxAlbumData>): RxCommunicatorContract.Emitter<RxAlbumData> {
        return rxCommunicator
    }


}