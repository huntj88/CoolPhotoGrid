package me.jameshunt.coolphotogrid.repo

import io.reactivex.Single
import me.jameshunt.coolphotogrid.repo.network.category.Category
import me.jameshunt.coolphotogrid.repo.network.photo.Photo
import retrofit2.http.GET

/**
 * Created by James on 10/5/2017.
 */
interface UnsplashService {

    @GET("/photos")
    fun getNewPhotos(): Single<List<Photo>>

    @GET("/categories")
    fun getCategories(): Single<List<Category>>

    @GET("/categories/6/photos")
    fun getCategoryPhotos(): Single<List<Photo>>
}