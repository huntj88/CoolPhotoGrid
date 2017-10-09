package me.jameshunt.coolphotogrid.di.page

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.feature.album.AlbumAdapter
import me.jameshunt.coolphotogrid.feature.album.AlbumContract
import me.jameshunt.coolphotogrid.feature.album.AlbumModel
import me.jameshunt.coolphotogrid.feature.album.AlbumPresenter
import me.jameshunt.coolphotogrid.feature.album.viewHolder.AlbumViewHolderFactory
import me.jameshunt.coolphotogrid.feature.album.viewHolder.AlbumViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.repo.api.album.SelectAlbumApiFactory
import javax.inject.Named

/**
 * Created by James on 10/9/2017.
 */

@Module
class AlbumModule {

    @PageScope
    @Provides
    fun getAlbumPresenter(selectAlbumApiFactory: SelectAlbumApiFactory, albumModel: AlbumContract.Model): AlbumContract.Presenter {
        return AlbumPresenter(selectAlbumApiFactory, albumModel)
    }


    @PageScope
    @Provides
    fun getAlbumModel(): AlbumContract.Model {
        return AlbumModel()
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