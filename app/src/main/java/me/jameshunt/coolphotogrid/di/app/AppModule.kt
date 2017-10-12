package me.jameshunt.coolphotogrid.di.app

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ActivityContract
import me.jameshunt.coolphotogrid.feature.activity.ActivityModel
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicator
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import javax.inject.Singleton

/**
 * Created by James on 10/5/2017.
 */
@Module
class AppModule {


    @Singleton
    @Provides
    fun getActivityModel(modelHolder: ModelHolder): ActivityContract.Model {
        return ActivityModel(modelHolder)
    }


    @Singleton
    @Provides
    fun getModelHolder(): ModelHolder {
        return ModelHolder()
    }



    @Singleton
    @Provides
    fun getAlbumClickedCommunicator(): RxCommunicator<RxAlbumData> {
        return RxCommunicator()
    }

    @Provides
    fun getAlbumClickedRxObserver(rxCommunicator: RxCommunicator<RxAlbumData>): RxCommunicatorContract.Observer<RxAlbumData> {
        return rxCommunicator
    }

    @Provides
    fun getAlbumClickedRxEmitter(rxCommunicator: RxCommunicator<RxAlbumData>): RxCommunicatorContract.Emitter<RxAlbumData> {
        return rxCommunicator
    }


}