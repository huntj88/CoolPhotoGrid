package me.jameshunt.coolphotogrid.feature.browse

import me.jameshunt.coolphotogrid.repo.api.BaseApi


/**
 * Created by James on 10/5/2017.
 */
interface BrowseContract {

    interface View {
        var presenter: Presenter

    }

    interface Model {
        var currentApi: BaseApi
    }

    interface Presenter {
        //val newPhotosObserver: RxCommunicatorContract.Observer<RxNewPhotos>
        //val newPhotosEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>


        fun viewLoaded()
    }
}