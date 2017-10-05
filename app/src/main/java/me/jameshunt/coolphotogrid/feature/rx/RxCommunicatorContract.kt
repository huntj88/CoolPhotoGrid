package me.jameshunt.coolphotogrid.feature.rx

import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * Created by James on 10/5/2017.
 */
interface RxCommunicatorContract {

    interface Emitter<Data> {
        var emitter: ObservableEmitter<Data>
    }

    interface Observer<Data> {
        fun getObservable(disposed: Boolean): Observable<Data>
    }

    companion object {
        const val INITIAL_DATA: String = "initial"
        const val ALBUM: String = "album"
        const val FAVORITES_ADDED: String = "favorites"
        const val IMAGE_CLICKED: String = "clicked"
    }
}