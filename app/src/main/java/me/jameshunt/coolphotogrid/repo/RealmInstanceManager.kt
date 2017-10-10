package me.jameshunt.coolphotogrid.repo

import io.realm.Realm



/**
 * Created by James on 10/5/2017.
 */

//todo: finish instance when isFinishing

class RealmInstanceManager {

    private var realm = Realm.getDefaultInstance()

    fun getRealm(): Realm {

        if(realm.isClosed)
            realm = Realm.getDefaultInstance()

        return realm
    }
}