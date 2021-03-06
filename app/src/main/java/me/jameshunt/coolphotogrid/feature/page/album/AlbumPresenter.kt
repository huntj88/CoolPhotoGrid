package me.jameshunt.coolphotogrid.feature.page.album

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import me.jameshunt.coolphotogrid.feature.page.album.viewHolder.AlbumViewType
import me.jameshunt.coolphotogrid.feature.page.album.viewHolder.SelectAlbumData
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.feature.rx.data.RxUnsplashSelectAlbumData
import me.jameshunt.coolphotogrid.repo.api.album.SelectAlbumApiFactory
import timber.log.Timber

/**
 * Created by James on 10/8/2017.
 */
class AlbumPresenter(

        private val albumClickedObserver: RxCommunicatorContract.Observer<RxAlbumData>,
        private val selectAlbumApiFactory: SelectAlbumApiFactory,
        val model: AlbumContract.Model

) : AlbumContract.Presenter {

    override lateinit var view: AlbumContract.View

    private val compDisposable = CompositeDisposable()

    override fun viewLoaded() {
        listenForApiData()
        listenForAlbumClick()
        setAlbumInfo()
    }

    private fun listenForApiData(requestMore: Boolean = false) {

        model.canRequestMore = false

        selectAlbumApiFactory.getApi(RxUnsplashSelectAlbumData(requestMore)).subscribeBy(
                onError = { it.printStackTrace() },
                onSuccess = {
                    val numBefore = model.amountBeforeRequest
                    model.currentApi = it
                    val numAfter = model.currentApi?.data?.size ?: 0

                    Timber.i("api in album has been updated")
                    Timber.i("album num: " + numAfter)

                    if (numBefore == 0) {
                        view.refreshRecycler()
                        view.hideLoadingAnimation()
                    } else {
                        view.insertItemsRecycler(numBefore, numAfter - numBefore)
                    }

                    model.amountBeforeRequest = numAfter
                    model.canRequestMore = true
                }
        )
    }

    private fun listenForAlbumClick() {
        val disposable = albumClickedObserver.getObservable(compDisposable.isDisposed).subscribeBy(
                onNext = { albumData ->
                    Timber.i("album click observed in album presenter")
                    model.selectedAlbum = albumData.album
                    setAlbumInfo()
                },
                onError = { it.printStackTrace() },
                onComplete = { Timber.i("album click complete in album presenter") }
        )

        compDisposable.add(disposable)
    }

    private fun setAlbumInfo() {
        model.selectedAlbum?.let {
            view.setAlbumInfo(it.title, it.numPhotos)
        }


    }

    override fun requestMore() {
        if (model.canRequestMore)
            listenForApiData(true)
    }

    override fun getItemViewType(position: Int): Int {
        return AlbumViewType.ALBUM.viewID
    }

    override fun getItemCount(): Int {
        return model.currentApi?.data?.size ?: 0
    }

    override fun getEnumForViewType(viewType: Int): AlbumViewType {
        return AlbumViewType.ALBUM
    }

    override fun getViewHolderData(position: Int): AdapterContract.ViewHolderData {
        return SelectAlbumData(model.currentApi?.data!![position])
    }

    override fun destroy() {
        compDisposable.dispose()
    }
}