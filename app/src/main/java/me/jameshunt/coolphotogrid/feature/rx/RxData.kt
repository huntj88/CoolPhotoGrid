package me.jameshunt.coolphotogrid.feature.rx

import io.realm.RealmResults
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/5/2017.
 */
data class RxData(

        val realmPhotos: RealmResults<RealmPhoto>,
        val index: Int = 0
) {
}