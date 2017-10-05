package me.jameshunt.coolphotogrid.di.app

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ActivityContract
import me.jameshunt.coolphotogrid.feature.activity.ActivityModel
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicator
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.RxData
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by James on 10/5/2017.
 */
@Module
class AppModule {


    @Singleton
    @Provides
    fun getActivityModel(): ActivityContract.Model {
        return ActivityModel()
    }


    @Singleton
    @Provides
    @Named(RxCommunicatorContract.INITIAL_DATA)
    fun getInitialRxCommunicator(): RxCommunicator<RxData> {
        return RxCommunicator()
    }

    @Singleton
    @Provides
    @Named(RxCommunicatorContract.INITIAL_DATA)
    fun getInitialDataRxObserver(@Named(RxCommunicatorContract.INITIAL_DATA) rxCommunicator: RxCommunicator<RxData>): RxCommunicatorContract.Observer<RxData> {
        return rxCommunicator
    }

    @Singleton
    @Provides
    @Named(RxCommunicatorContract.INITIAL_DATA)
    fun getInitialDataRxEmitter(@Named(RxCommunicatorContract.INITIAL_DATA) rxCommunicator: RxCommunicator<RxData>): RxCommunicatorContract.Emitter<RxData> {
        return rxCommunicator
    }


}