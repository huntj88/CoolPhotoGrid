package me.jameshunt.coolphotogrid.repo.network.unsplash

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
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
) {


    fun getRealmVersion(): RealmCollection {
        return RealmCollection(
                id = id,
                title = title,
                numPhotos = numPhotos,
                coverPhoto = coverPhoto.getRealmVersion()
        )
    }
}