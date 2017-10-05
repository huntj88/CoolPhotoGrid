package me.jameshunt.coolphotogrid.repo.api.setup

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.network.category.Category
import me.jameshunt.coolphotogrid.repo.realm.RealmCategory

/**
 * Created by James on 10/5/2017.
 */
class GetCategoriesApi(val unsplashService: UnsplashService) {

    fun getCategories() {
        unsplashService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { t: List<Category> -> saveToRealm(t) }
                .observeOn(AndroidSchedulers.mainThread())
                .toCompletable()
    }

    private fun saveToRealm(categories: List<Category>) {

        val realm = Realm.getDefaultInstance()

        realm.executeTransaction {
            for(category in categories) {
                val realmCategory = RealmCategory(category.id, category.title)
                it.insertOrUpdate(realmCategory)
            }
        }

        realm.close()
    }

}