package me.jameshunt.coolphotogrid.feature.page.album

import io.realm.RealmResults
import me.jameshunt.coolphotogrid.feature.page.Base
import me.jameshunt.coolphotogrid.feature.page.album.viewHolder.AlbumViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/8/2017.
 */
interface AlbumContract {

    interface View: Base.View, AdapterContract.AdapterView {
        var presenter: Presenter

        fun setAlbumInfo(albumName: String, numPhotos: Int)
    }

    interface Model {
        var currentApi: BaseApi<RealmResults<RealmCollection>>?
        var selectedAlbum: RealmCollection?

        var canRequestMore: Boolean
        var amountBeforeRequest: Int
    }

    interface Presenter: Base.Presenter, AdapterContract.Presenter<AlbumViewType> {
        var view: View

        fun viewLoaded()
    }
}