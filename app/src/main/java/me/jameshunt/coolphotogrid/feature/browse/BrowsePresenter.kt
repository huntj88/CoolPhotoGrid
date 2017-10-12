package me.jameshunt.coolphotogrid.feature.browse

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.realm.RealmList
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridData
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.api.photo.PhotoApiFactory
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto
import timber.log.Timber

/**
 * Created by James on 10/5/2017.
 */

class BrowsePresenter(
        private val albumClickedObserver: RxCommunicatorContract.Observer<RxAlbumData>,
        private val apiFactory: PhotoApiFactory,
        private val browseModel: BrowseContract.Model

) : BrowseContract.Presenter {

    override lateinit var view: BrowseContract.View

    //todo: dispose this
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun viewLoaded() {
        Timber.i("browse view loaded")

        observeAlbumClicked()
    }


    private fun observeAlbumClicked() {
        val disposable = albumClickedObserver.getObservable(compositeDisposable.isDisposed).subscribeBy(
                onNext = {
                    browseModel.amountBeforeRequest = it.album.photos?.size?:0
                    view.showLoadingAnimation()
                    listenForApiData(apiFactory.getApi(it))
                    Timber.i("album title: " + it.album.title)
                },
                onError = { it.printStackTrace() },
                onComplete = { Timber.i("album clicked request completed") }

        )
        compositeDisposable.add(disposable)
    }


    private fun listenForApiData(api: Single<BaseApi<RealmCollection>>) {

        browseModel.canRequestMore = false
        api.subscribeBy(
                onError = { it.printStackTrace() },
                onSuccess = {
                    val numBefore = browseModel.amountBeforeRequest
                    browseModel.currentApi = it
                    val numAfter = browseModel.currentApi?.data?.photos?.size?: 0

                    Timber.i("api in browse has been updated")


                    if(numBefore == 0) {
                        view.refreshRecycler()
                    } else {
                        view.insertItemsRecycler(numBefore, numAfter - numBefore)
                    }

                    view.hideLoadingAnimation()

                    browseModel.amountBeforeRequest = numAfter
                    browseModel.canRequestMore = true
                }
        )
    }

    override fun requestMore() {
        if (browseModel.canRequestMore)
            browseModel.currentApi?.let {

                val api = apiFactory.getApi(RxAlbumData(it.data, true))
                listenForApiData(api)
            }
    }

    override fun getItemViewType(position: Int): Int {


        // temp setup

        val remainder = position % 4

        return when (remainder) {
            0 -> GridViewType.ONE.viewID
            1 -> GridViewType.TWO.viewID
            2 -> GridViewType.THREE.viewID
            3 -> GridViewType.FOUR.viewID
            else -> {
                throw NotImplementedError("not implemented yet")
            }
        }


    }

    override fun getItemCount(): Int {

        // temp setup

        val numPhotos = browseModel.currentApi?.data?.photos?.size ?: 0

        val numPhotosRemainder = numPhotos % 11
        val numPhotosDivide = numPhotos / 11

        val divideToNumCells = numPhotosDivide * 4

        val remainderToNumCells = when (numPhotosRemainder) {
            0, 1 -> 1
            2, 3, 4 -> 2
            5, 6, 7 -> 3
            8, 9, 10 -> 4
            else -> {
                throw NotImplementedError("not set up for additional cells")
            }

        }

        return divideToNumCells + remainderToNumCells
    }

    override fun getEnumForViewType(viewType: Int): GridViewType {

        // temp setup
        return GridViewType.fromInt(viewType)!!
    }

    override fun getViewHolderData(position: Int): AdapterContract.ViewHolderData {


        // temp setup

        val gridViewType = GridViewType.fromInt(getItemViewType(position))
        val currentApi = browseModel.currentApi ?: throw UnsupportedOperationException("this should never happen")
        val photos = currentApi.data.photos


        /**
         *
         *  position * 11 / 4
         *
         *  11 images that can be displayed within 4 viewHolders
         *
         */

        return when (gridViewType) {
            GridViewType.ONE -> {

                //Timber.i("index of photo" + (position * 11 / 4))
                //Timber.i("index of photo" + (position * 11 / 4 + 1))


                val photo1 = checkIfImageAvailable(photos, position * 11 / 4)
                val photo2 = checkIfImageAvailable(photos, position * 11 / 4 + 1)
                GridData(listOf(photo1, photo2))
            }
            else -> {
                //Timber.i("index of photo" + (position * 11 / 4))
                //Timber.i("index of photo" + (position * 11 / 4 + 1))
                //Timber.i("index of photo" + (position * 11 / 4 + 2))

                val photo1 = checkIfImageAvailable(photos, position * 11 / 4)
                val photo2 = checkIfImageAvailable(photos, position * 11 / 4 + 1)
                val photo3 = checkIfImageAvailable(photos, position * 11 / 4 + 2)
                GridData(listOf(photo1, photo2, photo3))
            }
        }


    }

    private fun checkIfImageAvailable(photos: RealmList<RealmPhoto>?, index: Int): RealmPhoto? {

        if (photos?.size ?: 0 > index)
            return photos!![index]

        return null
    }
}