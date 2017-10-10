package me.jameshunt.coolphotogrid.di.activity

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ActivityContract
import me.jameshunt.coolphotogrid.feature.activity.ActivityPresenter
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.RealmInstanceManager
import me.jameshunt.coolphotogrid.repo.UnsplashService
import me.jameshunt.coolphotogrid.repo.api.album.SelectAlbumApiFactory
import me.jameshunt.coolphotogrid.repo.api.photo.newPhotos.PhotoApiFactory

/**
 * Created by James on 10/5/2017.
 */

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun getActivityPresenter(albumClickedObserver: RxCommunicatorContract.Observer<RxAlbumData>, model: ActivityContract.Model): ActivityContract.Presenter {
        return ActivityPresenter(albumClickedObserver, model)
    }

    @ActivityScope
    @Provides
    fun getPhotoApiFactory(unsplashService: UnsplashService, realmInstanceManager: RealmInstanceManager): PhotoApiFactory {
        return PhotoApiFactory(unsplashService, realmInstanceManager)
    }

    @ActivityScope
    @Provides
    fun getSelectAlbumApiFactory(unsplashService: UnsplashService, realmInstanceManager: RealmInstanceManager): SelectAlbumApiFactory {
        return SelectAlbumApiFactory(unsplashService, realmInstanceManager)
    }
}