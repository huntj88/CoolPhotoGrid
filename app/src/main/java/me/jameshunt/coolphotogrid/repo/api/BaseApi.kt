package me.jameshunt.coolphotogrid.repo.api

/**
 * Created by James on 10/5/2017.
 */
interface BaseApi<RealmData> {


    val data: RealmData
    val id: String
}