package me.jameshunt.coolphotogrid.repo.api.photo.albumPhotos

import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/12/2017.
 */
data class ViewAlbumApi(override val data: RealmCollection, override val id: String): BaseApi<RealmCollection>