package me.jameshunt.coolphotogrid.repo.api.album

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults
import me.jameshunt.coolphotogrid.feature.rx.data.RxUnsplashSelectAlbumData
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.network.unsplash.Collection
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */
class UnsplashSelectAlbumAccumulator(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager) {


    fun getDataFromRepo(data: RxUnsplashSelectAlbumData): Single<RealmResults<RealmCollection>> {



        return when(data.requestMore) {
            true -> requestMore()
            false -> fromCacheIfAvailable()
        }

    }

    private fun requestMore(): Single<RealmResults<RealmCollection>> {
        val cache = readCollectionsFromRealm()
        val recent = cache[cache.size - 1]

        val nextPage = recent.page + 1

        return webRequest(nextPage, 30)

    }

    private fun fromCacheIfAvailable(): Single<RealmResults<RealmCollection>> {
        val cache = readCollectionsFromRealm()

        return when(cache.size != 0) {
            true -> Single.just(cache)
            false -> webRequest(1, 30)
        }
    }

    private fun webRequest(pageNum: Int, amountToRequest: Int): Single<RealmResults<RealmCollection>> {
        return unsplashService.getCollections(pageNum, amountToRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { saveCollectionsToRealm(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .map { readCollectionsFromRealm() }
    }

    private fun saveCollectionsToRealm(collections: List<Collection>) {
        val compThreadRealm = Realm.getDefaultInstance()

        compThreadRealm.executeTransaction {
            for (collection in collections) {
                compThreadRealm.insertOrUpdate(collection.getRealmVersion())
            }
        }

        compThreadRealm.close()
    }

    private fun readCollectionsFromRealm(): RealmResults<RealmCollection> {


        var data: RealmResults<RealmCollection>? = null

        // if query is not in transaction sometimes the data is stale.
        realmInstanceManager.getRealm().executeTransaction {
            realm ->
            data = realm.where(RealmCollection::class.java).findAllSorted("page")
        }

        return data!!

    }


}