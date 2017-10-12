package me.jameshunt.coolphotogrid.repo.api.photo

import io.reactivex.Single
import me.jameshunt.coolphotogrid.feature.rx.RxData
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.api.photo.albumPhotos.ViewAlbumAccumulator
import me.jameshunt.coolphotogrid.repo.api.photo.albumPhotos.ViewAlbumApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/5/2017.
 */
class PhotoApiFactory(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager) {


    fun getApi(data: RxData): Single<BaseApi<RealmCollection>> {
        return when (data) {
            is RxAlbumData -> {
                buildViewAlbumApi(data)
            }

            else -> {
                throw NotImplementedError("not implemented yet")
            }
        }
    }

    private fun buildViewAlbumApi(data: RxAlbumData): Single<BaseApi<RealmCollection>> {
        val accumulator = ViewAlbumAccumulator(unsplashService, realmInstanceManager)
        return accumulator.getDataFromRepo(data).map { ViewAlbumApi(it,"") }
    }
}