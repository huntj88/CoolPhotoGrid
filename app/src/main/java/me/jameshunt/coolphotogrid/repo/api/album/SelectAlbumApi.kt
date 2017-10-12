package me.jameshunt.coolphotogrid.repo.api.album

import io.realm.RealmResults
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */

data class SelectAlbumApi(override val data: RealmResults<RealmCollection>, override val id: String): BaseApi<RealmResults<RealmCollection>>