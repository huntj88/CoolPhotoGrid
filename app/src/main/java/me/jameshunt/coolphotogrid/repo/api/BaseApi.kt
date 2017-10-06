package me.jameshunt.coolphotogrid.repo.api

import io.realm.RealmResults
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto

/**
 * Created by James on 10/5/2017.
 */
interface BaseApi {


    val photos: RealmResults<RealmPhoto>
    val id: String


    interface FactoryApi {

    }




    enum class API {
        NEW_PHOTOS,
        ALBUM, // don't forget to add a favorites category after initial load
    }




    //send rxData to factory which uses the api



    // don't return api from the factory unless it has either gotten a new photos set successfully, or the cache is still valid
    // if cache is invalid, but network call fails, then return the cache

    //cache should be kept for 5 minutes

    //the factory returns the api via a RxSingle. then just pull the photos out of it

}