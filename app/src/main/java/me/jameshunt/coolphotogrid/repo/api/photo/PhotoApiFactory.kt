package me.jameshunt.coolphotogrid.repo.api.photo

import io.reactivex.Single
import me.jameshunt.coolphotogrid.feature.rx.RxData
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/5/2017.
 */
class PhotoApiFactory(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager) {

    fun getApi(data: RxData): Single<BaseApi<RealmPhoto>> {
        return when (data) {
            is RxNewPhotos -> {
                NewPhotosAccumulator(unsplashService, realmInstanceManager).getDataFromRepo()
            }
            else -> {
                throw NotImplementedError("not implemented yet")
            }
        }
    }
}