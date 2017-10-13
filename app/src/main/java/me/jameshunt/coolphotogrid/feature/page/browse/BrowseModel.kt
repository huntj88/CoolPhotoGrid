package me.jameshunt.coolphotogrid.feature.page.browse

import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/5/2017.
 */
class BrowseModel: BrowseContract.Model {

    override var currentApi: BaseApi<RealmCollection>? = null

    override var canRequestMore: Boolean = false
    override var amountBeforeRequest: Int = 0
}