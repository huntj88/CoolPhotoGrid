package me.jameshunt.coolphotogrid.di.page

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.page.browse.BrowseContract
import me.jameshunt.coolphotogrid.feature.page.browse.BrowseModel
import me.jameshunt.coolphotogrid.feature.page.browse.BrowsePresenter
import me.jameshunt.coolphotogrid.feature.page.browse.CoolGridAdapter
import me.jameshunt.coolphotogrid.feature.page.browse.viewHolder.util.GridViewHolderFactory
import me.jameshunt.coolphotogrid.feature.page.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.api.photo.PhotoApiFactory
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
            apiFactory: PhotoApiFactory,
            browseModel: BrowseContract.Model

    ): BrowseContract.Presenter {
        return BrowsePresenter(albumClickedObserver, apiFactory, browseModel)
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


    @Provides
    fun getGridViewHolderFactory(): AdapterContract.ViewHolderFactory<GridViewType> {
        return GridViewHolderFactory()
    }

    @Provides
    @Named("browse")
    fun getGridAdapter(presenter: BrowseContract.Presenter, gridViewHolderFactory: AdapterContract.ViewHolderFactory<GridViewType>): AdapterContract.Adapter {

        return CoolGridAdapter(presenter, gridViewHolderFactory)
    }
}