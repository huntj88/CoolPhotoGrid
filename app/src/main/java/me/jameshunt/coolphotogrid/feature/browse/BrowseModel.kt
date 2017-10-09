package me.jameshunt.coolphotogrid.feature.browse

import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/5/2017.
 */
class BrowseModel: BrowseContract.Model {

    override var currentApi: BaseApi<RealmPhoto>? = null
}