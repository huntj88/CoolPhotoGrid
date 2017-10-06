package me.jameshunt.coolphotogrid.repo.api

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.network.photo.Photo
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto
import org.threeten.bp.Instant
import timber.log.Timber

/**
 * Created by James on 10/5/2017.
 */
data class NewPhotosApi(override val photos: RealmResults<RealmPhoto>, override val id: String): BaseApi



class NewPhotosAccumulator(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager): BaseApi.FactoryApi {


    fun getDataFromRepo(): Single<BaseApi> {

        val photosCache = getPhotosFromRealm()

        Timber.i("photo cache size: " + photosCache?.size)

        return when(photosCache.size == 0) {
            true -> {
                webRequest()
            }
            false -> {
                checkCacheAge(photosCache)
            }
        }

        // check cache to see if its still valid
        // if its not or can't cant anything do a web request, and return the results in an RxSingle
        // if it is valid return the cache

    }

    private fun webRequest(): Single<BaseApi> {
        return unsplashService.getNewPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { saveNetworkPhotos(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .map { wrapInApi(getPhotosFromRealm()) }
    }

    private fun checkCacheAge(photosCache: RealmResults<RealmPhoto>): Single<BaseApi> {

        val fiveMin = 5 * 60
        val cacheAgeMin = Instant.now().epochSecond - fiveMin

        val cacheValid = (photosCache[0].cacheAge > cacheAgeMin)

        return when(cacheValid) {
            true -> {
                val api = wrapInApi(photosCache)
                Single.just(api)
            }
            false -> {
                webRequest()
            }
        }

    }

    private fun saveNetworkPhotos(networkPhotos: List<Photo>) {
        val computationThreadRealm = Realm.getDefaultInstance()

        computationThreadRealm.executeTransaction {
            for(photo in networkPhotos)
                it.insertOrUpdate(photo.getRealmVersion())
        }

        computationThreadRealm.close()
    }


    private fun getPhotosFromRealm(): RealmResults<RealmPhoto> {
        return realmInstanceManager.getRealm().where(RealmPhoto::class.java).findAllSorted("unixTime",Sort.DESCENDING)
    }


    private fun wrapInApi(photos: RealmResults<RealmPhoto>): NewPhotosApi {
        return NewPhotosApi(photos,"")
    }

}