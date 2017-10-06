package me.jameshunt.coolphotogrid.feature.browse

import me.jameshunt.coolphotogrid.repo.api.BaseApi

/**
 * Created by James on 10/5/2017.
 */
class BrowseModel: BrowseContract.Model {

    override lateinit var currentApi: BaseApi
}