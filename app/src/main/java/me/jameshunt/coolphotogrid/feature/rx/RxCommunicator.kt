package me.jameshunt.coolphotogrid.feature.rx

import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * Created by James on 10/5/2017.
 */
class RxCommunicator<Data>: RxCommunicatorContract.Emitter<Data>, RxCommunicatorContract.Observer<Data> {

    override lateinit var emitter: ObservableEmitter<Data>
    private var observable: Observable<Data> = createNewObservable()

    override fun getObservable(disposed: Boolean): Observable<Data> {
        if(disposed)
            observable = createNewObservable()

        return observable
    }

    private fun createNewObservable(): Observable<Data> {
        return Observable.create{emitter = it}
    }
}