package me.jameshunt.coolphotogrid.feature.browse

import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection


/**
 * Created by James on 10/5/2017.
 */
interface BrowseContract {

    interface View: AdapterContract.AdapterView {
        var presenter: Presenter
    }

    interface Model {
        var currentApi: BaseApi<RealmCollection>?

        var canRequestMore: Boolean
        var amountBeforeRequest: Int
    }

    interface Presenter: AdapterContract.Presenter<GridViewType> {
        var view: View

        fun viewLoaded()
    }
}