package me.jameshunt.coolphotogrid.feature.rx.data

import me.jameshunt.coolphotogrid.feature.rx.RxData
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/5/2017.
 */

data class RxAlbumData(
        val album: RealmCollection
) : RxData