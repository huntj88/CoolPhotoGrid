package me.jameshunt.coolphotogrid.feature.album

import io.realm.RealmResults
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */
class AlbumModel: AlbumContract.Model {

    override var currentApi: BaseApi<RealmResults<RealmCollection>>? = null

    override var canRequestMore: Boolean = false
    override var amountBeforeRequest: Int = 0
}