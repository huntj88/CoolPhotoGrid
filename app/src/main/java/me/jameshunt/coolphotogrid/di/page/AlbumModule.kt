package me.jameshunt.coolphotogrid.di.page

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.activity.ModelHolder
import me.jameshunt.coolphotogrid.feature.album.AlbumAdapter
import me.jameshunt.coolphotogrid.feature.album.AlbumContract
import me.jameshunt.coolphotogrid.feature.album.AlbumModel
import me.jameshunt.coolphotogrid.feature.album.AlbumPresenter
import me.jameshunt.coolphotogrid.feature.album.viewHolder.AlbumViewHolderFactory
import me.jameshunt.coolphotogrid.feature.album.viewHolder.AlbumViewType
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
            albumClickedEmitter: RxCommunicatorContract.Emitter<RxAlbumData>,
            selectAlbumApiFactory: SelectAlbumApiFactory,
            albumModel: AlbumContract.Model

    ): AlbumContract.Presenter {
        return AlbumPresenter(albumClickedEmitter, selectAlbumApiFactory, albumModel)
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
    fun getAlbumViewHolderFactory(): AdapterContract.ViewHolderFactory<AlbumViewType> {
        return AlbumViewHolderFactory()
    }

    @PageScope
    @Provides
    @Named("album")
    fun getAlbumAdapter(presenter: AlbumContract.Presenter, albumViewHolderFactory: AdapterContract.ViewHolderFactory<AlbumViewType>): AdapterContract.Adapter {

        return AlbumAdapter(presenter, albumViewHolderFactory)
    }

}