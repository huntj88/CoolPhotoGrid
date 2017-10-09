package me.jameshunt.coolphotogrid.feature.album

import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/8/2017.
 */
interface AlbumContract {

    interface View: AdapterContract.AdapterView {
        var presenter: Presenter

    }

    interface Model {

    }

    interface Presenter: AdapterContract.Presenter<AlbumViewType> {
        var view: View
        fun viewLoaded()
    }
}