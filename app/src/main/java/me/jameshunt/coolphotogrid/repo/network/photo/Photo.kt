package me.jameshunt.coolphotogrid.repo.network.photo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto
import me.jameshunt.coolphotogrid.repo.realm.RealmPhotoUrls
import me.jameshunt.coolphotogrid.repo.realm.RealmPhotoUser
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


data class Photo(

        @Expose val id: String = "",
        @SerializedName("created_at") val createdAt: String,
        @Expose val width: Int = 0,
        @Expose val height: Int = 0,
        @Expose val description: String? = null,
        @Expose val user: User? = null,
        @Expose val urls: Urls? = null

) {

    fun getRealmVersion(): RealmPhoto {

        val unixTime = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME).toEpochSecond(ZoneOffset.UTC)

        val descriptionCheck = description?:""

        val photoUser = RealmPhotoUser(user?.name?:"", user?.links?.html?:"")

        val cacheAge: Int = Instant.now().epochSecond.toInt()

        val realmUrls = RealmPhotoUrls(
                urls?.raw?:"",
                urls?.full?:"",
                urls?.regular?:"",
                urls?.small?:"",
                urls?.thumb?:""
        )

        return RealmPhoto(
                id = id,
                unixTime = unixTime,
                width = width,
                height = height,
                description = descriptionCheck,
                user = photoUser,
                urls = realmUrls,
                cacheAge = cacheAge
        )
    }
}