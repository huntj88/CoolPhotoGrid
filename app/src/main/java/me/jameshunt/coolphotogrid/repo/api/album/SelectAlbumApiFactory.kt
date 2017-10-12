package me.jameshunt.coolphotogrid.repo.api.album

import io.reactivex.Single
import io.realm.RealmResults
import me.jameshunt.coolphotogrid.feature.rx.RxData
import me.jameshunt.coolphotogrid.feature.rx.data.RxUnsplashSelectAlbumData
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */
class SelectAlbumApiFactory(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager) {


    fun getApi(data: RxData): Single<BaseApi<RealmResults<RealmCollection>>> {

        return when(data) {
            is RxUnsplashSelectAlbumData -> {
                buildUnsplashSelectAlbumApi(data)
            }
            else -> {
                throw NotImplementedError("not implemented yet")
            }
        }

    }

    private fun buildUnsplashSelectAlbumApi(data: RxUnsplashSelectAlbumData): Single<BaseApi<RealmResults<RealmCollection>>> {
        val accumulator = UnsplashSelectAlbumAccumulator(unsplashService, realmInstanceManager)
        return accumulator.getDataFromRepo(data)
                .map {
                    SelectAlbumApi(it, "")
                }
    }

}