package me.jameshunt.coolphotogrid.feature.browse

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos
import me.jameshunt.coolphotogrid.repo.api.BaseApi
import me.jameshunt.coolphotogrid.repo.api.ApiFactory
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

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun viewLoaded() {
        Timber.i("browse view loaded")
        observeNewPhotosRequest()
        newPhotosEmitter.emitter.onNext(RxNewPhotos())
    }


    private fun observeNewPhotosRequest() {
        val disposable = newPhotosObserver.getObservable(compositeDisposable.isDisposed).subscribeBy(
                onNext = {
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
                    //TODO: update ui
                }
        )
    }
}