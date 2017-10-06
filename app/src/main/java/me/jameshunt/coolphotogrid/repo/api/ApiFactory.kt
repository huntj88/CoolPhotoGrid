package me.jameshunt.coolphotogrid.repo.api

import io.reactivex.Single
import me.jameshunt.coolphotogrid.feature.rx.RxData
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService

/**
 * Created by James on 10/5/2017.
 */
class ApiFactory(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager) {

    fun getApi(data: RxData): Single<BaseApi> {
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