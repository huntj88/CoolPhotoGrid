package me.jameshunt.coolphotogrid.repo.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by James on 10/9/2017.
 */
open class RealmCollection (

        @PrimaryKey
        open var id: Int = 0,

        open var title: String = "",
        open var numPhotos: Int = 0,
        open var coverPhoto: RealmPhoto? = null

): RealmObject()