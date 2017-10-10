package me.jameshunt.coolphotogrid.repo.network.unsplash.interceptors

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.jameshunt.coolphotogrid.repo.network.unsplash.Collection
import okhttp3.*


/**
 * Created by James on 10/9/2017.
 */

class HeaderFieldInjectorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {


        val request = chain.request()

        val originalResponse = chain.proceed(request)

        if (!request.url().encodedPath().toLowerCase().contains("/collections/featured"))
            return originalResponse

        return putPageNumInBody(request, originalResponse)
    }


    private fun putPageNumInBody(request: Request, response: Response): Response {
        val originalBody = response.body()!!.string()

        val collections = Gson().fromJson<List<Collection>>(originalBody)

        for (collection in collections) {
            collection.pageNum = request.url().queryParameterValues("page")[0].toInt()
        }

        val newBody = Gson().toJson(collections)

        val contentType = response.body()?.contentType() ?: MediaType.parse("application/json")

        val body = ResponseBody.create(contentType, newBody)
        return response.newBuilder().body(body).build()
    }


    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}