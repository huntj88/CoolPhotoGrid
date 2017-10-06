package me.jameshunt.coolphotogrid.feature.rx.data

import io.realm.RealmResults
import me.jameshunt.coolphotogrid.feature.rx.RxData
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/5/2017.
 */
data class RxImageClicked(

        val id: String,
        val photos: RealmResults<RealmPhoto>

) : RxData

/**
 *
 *  "id" is the id of the image
 *  "photos" is the results of the images that are currently being displayed by whatever api
 *
 *  this type gets converted into an api that just returns the photos
 *
 *
 */