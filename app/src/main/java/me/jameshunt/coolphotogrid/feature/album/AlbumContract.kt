package me.jameshunt.coolphotogrid.feature.album

import me.jameshunt.coolphotogrid.feature.album.viewHolder.AlbumViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/8/2017.
 */
interface AlbumContract {

    interface View: AdapterContract.AdapterView {
        var presenter: Presenter

    }

    interface Model {
        var currentApi: BaseApi<RealmCollection>?

        var canRequestMore: Boolean
        var amountBeforeRequest: Int
    }

    interface Presenter: AdapterContract.Presenter<AlbumViewType> {
        var view: View

        fun viewLoaded()
        fun requestMore()
    }
}