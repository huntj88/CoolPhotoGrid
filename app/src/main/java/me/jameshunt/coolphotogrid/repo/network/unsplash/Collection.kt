package me.jameshunt.coolphotogrid.repo.network.unsplash

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import me.jameshunt.coolphotogrid.repo.network.unsplash.photo.Photo
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */
class Collection (
        @Expose val id: Int = 0,
        @Expose val title: String = "",
        @SerializedName("total_photos") val numPhotos: Int,
        @SerializedName("cover_photo") val coverPhoto: Photo
): ResponsePage {

    // set in okhttp interceptor "HeaderFieldInjectorInterceptor"
    override var pageNum: Int = 1

    fun getRealmVersion(): RealmCollection {
        return RealmCollection(
                id = id,
                title = title,
                numPhotos = numPhotos,
                coverPhoto = coverPhoto.getRealmVersion(),
                photos = RealmList(),
                page = pageNum

        )
    }
}