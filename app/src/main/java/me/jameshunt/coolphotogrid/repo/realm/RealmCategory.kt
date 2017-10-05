package me.jameshunt.coolphotogrid.repo.realm

import io.realm.RealmObject

/**
 * Created by James on 10/5/2017.
 */
open class RealmCategory(

        open var id: Int = 0,
        open var title: String = ""

): RealmObject() {
}