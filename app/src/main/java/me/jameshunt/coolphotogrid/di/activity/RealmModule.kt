package me.jameshunt.coolphotogrid.di.activity

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager

/**
 * Created by James on 10/5/2017.
 */
@Module
class RealmModule {

    @Provides
    @ActivityScope
    fun getUIRealm(): RealmInstanceManager {
        return RealmInstanceManager()
    }
}