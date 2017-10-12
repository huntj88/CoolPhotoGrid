package me.jameshunt.coolphotogrid.repo.api.photo.albumPhotos

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.network.unsplash.photo.Photo
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection
import timber.log.Timber

/**
 * Created by James on 10/12/2017.
 */
class ViewAlbumAccumulator(private val unsplashService: UnsplashService, private val realmInstanceManager: RealmInstanceManager) {

    fun getDataFromRepo(data: RxAlbumData): Single<RealmCollection> {

        return when(data.requestMore) {
            true -> requestMore(data)
            false -> fromCacheIfAvailable(data)
        }
    }

    private fun fromCacheIfAvailable(data: RxAlbumData): Single<RealmCollection> {
        val cache = data.album.photos?.where()?.findAllSorted("page")

        return when(cache != null && cache.size != 0) {
            true -> Single.just(data.album)
            false -> webRequest(data.album.id, 1, 30)
        }
    }

    private fun requestMore(data: RxAlbumData): Single<RealmCollection> {

        val cache = data.album.photos?.where()?.findAllSorted("page")

        val recentPageNum: Int = cache?.let { cache[cache.size - 1].page }?: 0

        val nextPage = recentPageNum + 1

        return webRequest(data.album.id, nextPage, 30)

    }

    private fun webRequest(albumID: Int, pageNum: Int, amountToRequest: Int): Single<RealmCollection> {
        return unsplashService.getPhotosFromCollection(albumID, pageNum, amountToRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { savePhotosToRealm(albumID, it) }
                .observeOn(AndroidSchedulers.mainThread())
                .map { readPhotosFromRealm(albumID) }
    }

    private fun savePhotosToRealm(albumID: Int, photos: List<Photo>) {

        val compRealm = Realm.getDefaultInstance()

        compRealm.executeTransaction {
            val album = it.where(RealmCollection::class.java).equalTo("id", albumID).findFirst()

            for(photo in photos) {
                val managedPhoto = it.copyToRealmOrUpdate(photo.getRealmVersion())
                album?.photos?.add(managedPhoto)?: (Timber.w("could not add photo to album"))
            }

        }

        compRealm.close()

    }

    private fun readPhotosFromRealm(albumID: Int): RealmCollection {

        var album: RealmCollection? = null

        realmInstanceManager.getRealm().executeTransaction {
            album = it.where(RealmCollection::class.java).equalTo("id", albumID).findFirst()
        }

        return album!!
    }

}