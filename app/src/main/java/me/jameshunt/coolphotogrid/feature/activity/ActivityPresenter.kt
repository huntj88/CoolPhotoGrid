package me.jameshunt.coolphotogrid.feature.activity

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import timber.log.Timber

/**
 * Created by James on 10/5/2017.
 */
class ActivityPresenter(private val albumClickedObserver: RxCommunicatorContract.Observer<RxAlbumData>, override val model: ActivityContract.Model): ActivityContract.Presenter {

    override lateinit var view: ActivityContract.View

    private val compositeDisposable = CompositeDisposable()

    override fun setupAndWait() {

        val disposable = albumClickedObserver.getObservable(compositeDisposable.isDisposed).subscribeBy(
                onNext = {
                    Timber.i("click info: " + it.id)
                    view.showBrowse()
                },
                onError = {it.printStackTrace()},
                onComplete = {Timber.i("album clicked observer complete")}
        )

        compositeDisposable.add(disposable)
    }

    override fun destroy() {
        compositeDisposable.dispose()
    }
}