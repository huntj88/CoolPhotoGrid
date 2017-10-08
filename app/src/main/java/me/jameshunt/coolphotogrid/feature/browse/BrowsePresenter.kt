package me.jameshunt.coolphotogrid.feature.browse

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.realm.RealmResults
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridData
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.api.ApiFactory
import me.jameshunt.coolphotogrid.repo.realm.RealmPhoto
import timber.log.Timber

/**
 * Created by James on 10/5/2017.
 */

class BrowsePresenter(

        private val newPhotosObserver: RxCommunicatorContract.Observer<RxNewPhotos>,
        private val newPhotosEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>,
        private val apiFactory: ApiFactory,
        private val browseModel: BrowseContract.Model

) : BrowseContract.Presenter {

    override lateinit var view: BrowseContract.View

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun viewLoaded() {
        Timber.i("browse view loaded")
        observeNewPhotosRequest()
        newPhotosEmitter.emitter.onNext(RxNewPhotos())
    }


    private fun observeNewPhotosRequest() {
        val disposable = newPhotosObserver.getObservable(compositeDisposable.isDisposed).subscribeBy(
                onNext = {
                    view.showLoadingAnimation()
                    listenForApiData(apiFactory.getApi(it))
                },
                onError = { it.printStackTrace() },
                onComplete = {}

        )
        compositeDisposable.add(disposable)
    }


    private fun listenForApiData(api: Single<BaseApi>) {
        api.subscribeBy(
                onError = { it.printStackTrace() },
                onSuccess = {
                    browseModel.currentApi = it
                    Timber.i("api in browse has been updated")
                    view.updateRecycler()
                    view.hideLoadingAnimation()
                }
        )
    }


    override fun getItemViewType(position: Int): Int {


        // temp setup

        val remainder = position % 4

        return when(remainder) {
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

        val numPhotos = browseModel.currentApi?.photos?.size?:0

        val numPhotosRemainder = numPhotos % 11
        val numPhotosDivide = numPhotos / 11

        val divideToNumCells = numPhotosDivide * 4

        val remainderToNumCells = when(numPhotosRemainder) {
            0, 1 -> 1
            2,3,4 -> 2
            5,6,7 -> 3
            8,9,10 -> 4
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
        val currentApi = browseModel.currentApi?: throw UnsupportedOperationException("this should never happen")
        val photos = currentApi.photos

        return when(gridViewType){
            GridViewType.ONE -> {

                //Timber.i("index of photo" + (position * 11 / 4))
                //Timber.i("index of photo" + (position * 11 / 4 + 1))


                val photo1 = checkIfImageAvailable(photos, position * 2)
                val photo2 = checkIfImageAvailable(photos, position * 2 + 1)
                GridData(listOf(photo1, photo2))
            }
            else -> {
                //Timber.i("index of photo" + (position * 11 / 4))
                //Timber.i("index of photo" + (position * 11 / 4 + 1))
                //Timber.i("index of photo" + (position * 11 / 4 + 2))

                val photo1 = checkIfImageAvailable(photos, position * 3)
                val photo2 = checkIfImageAvailable(photos, position * 3 + 1)
                val photo3 = checkIfImageAvailable(photos, position * 3 + 2)
                GridData(listOf(photo1, photo2, photo3))
            }
        }




    }

    fun checkIfImageAvailable(photos: RealmResults<RealmPhoto>, index: Int): RealmPhoto? {

        if(photos.size > index)
            return photos[index]

        return null
    }
}