package me.jameshunt.coolphotogrid.feature.page.browse

import me.jameshunt.coolphotogrid.feature.page.Base
import me.jameshunt.coolphotogrid.feature.page.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection


/**
 * Created by James on 10/5/2017.
 */
interface BrowseContract {

    interface View: Base.View, AdapterContract.AdapterView {
        var presenter: Presenter
    }

    interface Model {
        var currentApi: BaseApi<RealmCollection>?

        var canRequestMore: Boolean
        var amountBeforeRequest: Int
    }

    interface Presenter: Base.Presenter, AdapterContract.Presenter<GridViewType> {
        var view: View

        fun viewLoaded()
    }
}