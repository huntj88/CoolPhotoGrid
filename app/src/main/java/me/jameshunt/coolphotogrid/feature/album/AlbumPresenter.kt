package me.jameshunt.coolphotogrid.feature.album

import io.reactivex.rxkotlin.subscribeBy
import me.jameshunt.coolphotogrid.feature.album.viewHolder.AlbumViewType
import me.jameshunt.coolphotogrid.feature.album.viewHolder.SelectAlbumData
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxUnsplashSelectAlbumData
import me.jameshunt.coolphotogrid.repo.api.album.SelectAlbumApiFactory
import timber.log.Timber

/**
 * Created by James on 10/8/2017.
 */
class AlbumPresenter(val selectAlbumApiFactory: SelectAlbumApiFactory, val model: AlbumContract.Model) : AlbumContract.Presenter {

    override lateinit var view: AlbumContract.View

    override fun viewLoaded() {
        listenForApiData()
    }

    private fun listenForApiData() {
        selectAlbumApiFactory.getApi(RxUnsplashSelectAlbumData()).subscribeBy(
                onError = { it.printStackTrace() },
                onSuccess = {
                    model.currentApi = it
                    Timber.i("api in album has been updated")
                    Timber.i("album num: " + it.data.size)
                    view.updateRecycler()
                    view.hideLoadingAnimation()
                }
        )
    }

    override fun getItemViewType(position: Int): Int {
        return AlbumViewType.ALBUM.viewID
    }

    override fun getItemCount(): Int {
        return model.currentApi?.data?.size?:0
    }

    override fun getEnumForViewType(viewType: Int): AlbumViewType {
        return AlbumViewType.ALBUM
    }

    override fun getViewHolderData(position: Int): AdapterContract.ViewHolderData {
        return SelectAlbumData(model.currentApi?.data!![position])
    }
}