package me.jameshunt.coolphotogrid.repo

import io.reactivex.Single
import me.jameshunt.coolphotogrid.repo.network.unsplash.Collection
import me.jameshunt.coolphotogrid.repo.network.unsplash.photo.Photo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by James on 10/5/2017.
 */
interface UnsplashService {

    @GET("/collections/featured")
    fun getCollections(@Query("page") pageNum: Int, @Query("per_page") amountToRequest: Int): Single<List<Collection>>

    @GET("collections/{id}/photos")
    fun getPhotosFromCollection(@Path("id") id: Int, @Query("page") pageNum: Int, @Query("per_page") amountToRequest: Int): Single<List<Photo>>



}