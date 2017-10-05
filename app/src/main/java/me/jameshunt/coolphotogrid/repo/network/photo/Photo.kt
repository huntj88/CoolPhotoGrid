package me.jameshunt.coolphotogrid.repo.network.photo

import com.google.gson.annotations.Expose
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto
import me.jameshunt.coolphotogrid.repo.realm.RealmPhotoUrls
import me.jameshunt.coolphotogrid.repo.realm.RealmPhotoUser

data class Photo(

        @Expose val id: String = "",
        @Expose val width: Int = 0,
        @Expose val height: Int = 0,
        @Expose val description: String? = null,
        @Expose val user: User? = null,
        @Expose val urls: Urls? = null

) {

    fun getRealmVersion(): RealmPhoto {

        val descriptionCheck = description?:""

        val photoUser = RealmPhotoUser(user?.name?:"", user?.links?.html?:"")

        val realmUrls = RealmPhotoUrls(
                urls?.raw?:"",
                urls?.full?:"",
                urls?.regular?:"",
                urls?.small?:"",
                urls?.thumb?:""
        )

        return RealmPhoto(id = id, width = width, height = height, description = descriptionCheck, user = photoUser, urls = realmUrls)
    }
}