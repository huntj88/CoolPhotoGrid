package me.jameshunt.coolphotogrid.repo.network.unsplash.interceptors

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.jameshunt.coolphotogrid.repo.network.unsplash.Collection
import me.jameshunt.coolphotogrid.repo.network.unsplash.ResponsePage
import me.jameshunt.coolphotogrid.repo.network.unsplash.photo.Photo
import okhttp3.*


/**
 * Created by James on 10/9/2017.
 */

class HeaderFieldInjectorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {


        val request = chain.request()

        val originalResponse = chain.proceed(request)

        val url = request.url().encodedPath().toLowerCase()

        if (!url.contains("/collections/"))
            return originalResponse

        if (url.contains("/featured")) {
            return putPageNumInCollectionBody(request, originalResponse)
        } else if (url.contains("/photos"))
            return putPageNumInPhotoBody(request, originalResponse)


        return originalResponse
    }


    private fun putPageNumInCollectionBody(request: Request, response: Response): Response {
        val originalBody = response.body()!!.string()
        val dataList = Gson().fromJson<List<Collection>>(originalBody)

        insertPageNum(dataList, request)
        val newBody = Gson().toJson(dataList)

        return buildBody(newBody, response)
    }


    private fun putPageNumInPhotoBody(request: Request, response: Response): Response {
        val originalBody = response.body()!!.string()
        val dataList = Gson().fromJson<List<Photo>>(originalBody)

        insertPageNum(dataList, request)
        val newBody = Gson().toJson(dataList)

        return buildBody(newBody, response)
    }

    private fun insertPageNum(dataList: List<ResponsePage>, request: Request) {
        for (data in dataList) {
            data.pageNum = request.url().queryParameterValues("page")[0].toInt()
        }
    }

    private fun buildBody(newBody: String, response: Response): Response {
        val contentType = response.body()?.contentType() ?: MediaType.parse("application/json")

        val body = ResponseBody.create(contentType, newBody)
        return response.newBuilder().body(body).build()
    }


    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}