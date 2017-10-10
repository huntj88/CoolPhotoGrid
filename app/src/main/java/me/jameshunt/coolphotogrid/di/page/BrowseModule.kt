package me.jameshunt.coolphotogrid.di.page

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.browse.BrowseContract
import me.jameshunt.coolphotogrid.feature.browse.BrowseModel
import me.jameshunt.coolphotogrid.feature.browse.BrowsePresenter
import me.jameshunt.coolphotogrid.feature.browse.CoolGridAdapter
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewHolderFactory
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.api.photo.newPhotos.PhotoApiFactory
import javax.inject.Named

/**
 * Created by James on 10/5/2017.
 */

@Module
class BrowseModule {

    @PageScope
    @Provides
    fun getBrowsePresenter(
            albumClickedObserver: RxCommunicatorContract.Observer<RxAlbumData>,
            newPhotosObserver: RxCommunicatorContract.Observer<RxNewPhotos>,
            newPhotosEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>,
            apiFactory: PhotoApiFactory,
            browseModel: BrowseContract.Model

    ): BrowseContract.Presenter {
        return BrowsePresenter(albumClickedObserver, newPhotosObserver, newPhotosEmitter, apiFactory, browseModel)
    }

    @PageScope
    @Provides
    fun getBrowseModel(modelHolder: ModelHolder): BrowseContract.Model {

        val model: BrowseContract.Model

        return when(modelHolder.browseModel == null) {
            true -> {
                model = BrowseModel()
                modelHolder.browseModel = model
                model
            }
            false -> {
                model = modelHolder.browseModel!!
                model
            }
        }
    }


    @PageScope
    @Provides
    fun getGridViewHolderFactory(): AdapterContract.ViewHolderFactory<GridViewType> {
        return GridViewHolderFactory()
    }

    @PageScope
    @Provides
    @Named("browse")
    fun getGridAdapter(presenter: BrowseContract.Presenter, gridViewHolderFactory: AdapterContract.ViewHolderFactory<GridViewType>): AdapterContract.Adapter {

        return CoolGridAdapter(presenter, gridViewHolderFactory)
    }
}