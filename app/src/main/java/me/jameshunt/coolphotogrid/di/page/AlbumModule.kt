package me.jameshunt.coolphotogrid.di.page

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.page.album.AlbumAdapter
import me.jameshunt.coolphotogrid.feature.page.album.AlbumContract
import me.jameshunt.coolphotogrid.feature.page.album.AlbumModel
import me.jameshunt.coolphotogrid.feature.page.album.AlbumPresenter
import me.jameshunt.coolphotogrid.feature.page.album.viewHolder.AlbumViewHolderFactory
import me.jameshunt.coolphotogrid.feature.page.album.viewHolder.AlbumViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.api.album.SelectAlbumApiFactory
import javax.inject.Named

/**
 * Created by James on 10/9/2017.
 */

@Module
class AlbumModule {

    @PageScope
    @Provides
    fun getAlbumPresenter(
            albumClickedObserver: RxCommunicatorContract.Observer<RxAlbumData>,
            selectAlbumApiFactory: SelectAlbumApiFactory,
            albumModel: AlbumContract.Model

    ): AlbumContract.Presenter {
        return AlbumPresenter(albumClickedObserver, selectAlbumApiFactory, albumModel)
    }


    @PageScope
    @Provides
    fun getAlbumModel(modelHolder: ModelHolder): AlbumContract.Model {

        val model: AlbumContract.Model

        return when(modelHolder.albumModel == null) {
            true -> {
                model = AlbumModel()
                modelHolder.albumModel = model
                model
            }
            false -> {
                model = modelHolder.albumModel!!
                model
            }
        }
    }


    @PageScope
    @Provides
    fun getAlbumViewHolderFactory(albumClickedEmitter: RxCommunicatorContract.Emitter<RxAlbumData>): AdapterContract.ViewHolderFactory<AlbumViewType> {
        return AlbumViewHolderFactory(albumClickedEmitter)
    }

    @PageScope
    @Provides
    @Named("album")
    fun getAlbumAdapter(presenter: AlbumContract.Presenter, albumViewHolderFactory: AdapterContract.ViewHolderFactory<AlbumViewType>): AdapterContract.Adapter {

        return AlbumAdapter(presenter, albumViewHolderFactory)
    }

}