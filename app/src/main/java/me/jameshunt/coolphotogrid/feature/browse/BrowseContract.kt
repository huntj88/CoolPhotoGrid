package me.jameshunt.coolphotogrid.feature.browse

import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.api.BaseApi


/**
 * Created by James on 10/5/2017.
 */
interface BrowseContract {

    interface View {
        var presenter: Presenter
        fun updateRecycler()
        fun showLoadingAnimation()
        fun hideLoadingAnimation()

    }

    interface Model {
        var currentApi: BaseApi?
    }

    interface Presenter: AdapterContract.Presenter<GridViewType> {
        //val newPhotosObserver: RxCommunicatorContract.Observer<RxNewPhotos>
        //val newPhotosEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>

        var view: View


        fun viewLoaded()
    }
}