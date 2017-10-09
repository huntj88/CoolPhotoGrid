package me.jameshunt.coolphotogrid.repo.api.album

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toSingle
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.network.unsplash.Collection
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */
class UnsplashSelectAlbumAccumulator(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager) {


    fun getDataFromRepo(): Single<RealmResults<RealmCollection>> {

        val cache = readCollectionsFromRealm()

        if (cache.size != 0)
            return cache.toSingle()



        return unsplashService.getCollections()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { saveCollectionsToRealm(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .map { readCollectionsFromRealm() }


    }

    private fun saveCollectionsToRealm(collections: List<Collection>) {
        val compThreadRealm = Realm.getDefaultInstance()

        compThreadRealm.executeTransaction {
            for(collection in collections) {
                compThreadRealm.insertOrUpdate(collection.getRealmVersion())
            }
        }

        compThreadRealm.close()
    }

    private fun readCollectionsFromRealm(): RealmResults<RealmCollection> {
        return realmInstanceManager.getRealm().where(RealmCollection::class.java).findAllSorted("id")
    }




}