package me.jameshunt.coolphotogrid.repo

import io.reactivex.Single
import me.jameshunt.coolphotogrid.repo.network.unsplash.Collection
import me.jameshunt.coolphotogrid.repo.network.unsplash.photo.Photo
import retrofit2.http.GET

/**
 * Created by James on 10/5/2017.
 */
interface UnsplashService {

    @GET("/photos")
    fun getNewPhotos(): Single<List<Photo>>



    @GET("/collections/featured")
    fun getCollections(): Single<List<Collection>>



}