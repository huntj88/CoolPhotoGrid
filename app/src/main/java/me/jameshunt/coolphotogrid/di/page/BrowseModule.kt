package me.jameshunt.coolphotogrid.di.page

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.browse.BrowseContract
import me.jameshunt.coolphotogrid.feature.browse.BrowseModel
import me.jameshunt.coolphotogrid.feature.browse.BrowsePresenter
import me.jameshunt.coolphotogrid.feature.browse.CoolGridAdapter
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewHolderFactory
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.api.ApiFactory

/**
 * Created by James on 10/5/2017.
 */

@Module
class BrowseModule {

    @PageScope
    @Provides
    fun getBrowsePresenter(

            newPhotosObserver: RxCommunicatorContract.Observer<RxNewPhotos>,
            newPhotosEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>,
            apiFactory: ApiFactory,
            browseModel: BrowseContract.Model

    ): BrowseContract.Presenter {
        return BrowsePresenter(newPhotosObserver, newPhotosEmitter, apiFactory, browseModel)
    }

    @PageScope
    @Provides
    fun getBrowseModel(): BrowseContract.Model {
        return BrowseModel()
    }


    @PageScope
    @Provides
    fun getGridViewHolderFactory(): AdapterContract.ViewHolderFactory<GridViewType> {
        return GridViewHolderFactory()
    }

    @PageScope
    @Provides
    fun getGridAdapter(presenter: BrowseContract.Presenter, gridViewHolderFactory: AdapterContract.ViewHolderFactory<GridViewType>): AdapterContract.Adapter {

        return CoolGridAdapter(presenter, gridViewHolderFactory)
    }
}