package me.jameshunt.coolphotogrid.repo.api

import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Created by James on 10/5/2017.
 */
interface BaseApi<RealmData: RealmObject> {


    val data: RealmResults<RealmData>
    val id: String


    interface FactoryApi {

    }




    enum class API {
        NEW_PHOTOS,
        ALBUM, // don't forget to add a favorites category after initial load
        SELECT_ALBUM
    }




    //send rxData to factory which uses the api



    // don't return api from the factory unless it has either gotten a new data set successfully, or the cache is still valid
    // if cache is invalid, but network call fails, then return the cache

    //cache should be kept for 5 minutes

    //the factory returns the api via a RxSingle. then just pull the data out of it

}