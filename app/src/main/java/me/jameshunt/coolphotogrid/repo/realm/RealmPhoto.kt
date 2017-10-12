package me.jameshunt.coolphotogrid.repo.realm

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

/**
 * Created by James on 10/5/2017.
 */

open class RealmPhoto(

        @PrimaryKey
        open var id: String = "",

        @Index
        open var page: Int = 0,

        @Index
        open var unixTime: Long = 0,

        open var width: Int = 0,
        open var height: Int = 0,
        open var description: String = "",
        open var user: RealmPhotoUser? = RealmPhotoUser(),
        open var urls: RealmPhotoUrls? = RealmPhotoUrls(),

        open var cacheAge: Int = 0

) : RealmObject()


open class RealmPhotoUser(

        open var name: String = "",
        open var link: String = ""

) : RealmObject()

open class RealmPhotoUrls(

        open var raw: String = "",
        open var full: String = "",
        open var regular: String = "",
        open var small: String = "",
        open var thumb: String = ""

) : RealmObject()