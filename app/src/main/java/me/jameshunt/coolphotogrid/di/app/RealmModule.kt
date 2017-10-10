package me.jameshunt.coolphotogrid.di.app

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import javax.inject.Singleton

/**
 * Created by James on 10/5/2017.
 */
@Module
class RealmModule {

    @Provides
    @Singleton
    fun getUIRealm(): RealmInstanceManager {
        return RealmInstanceManager()
    }
}