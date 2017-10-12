package me.jameshunt.coolphotogrid.feature.activity

import me.jameshunt.coolphotogrid.feature.activity.slide.SlideOnTouch
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos

/**
 * Created by James on 10/5/2017.
 */
class ActivityContract {

    interface View {
        var presenter: Presenter

        fun showBrowse()
        fun showViewer()
    }

    interface Model {
        val newPhotosEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>
        val modelHolder: ModelHolder
        fun canAlbumSlideUp(): Boolean
    }

    interface Presenter: SlideOnTouch.PresenterForSlide {
        val model: Model
        var view: View

        fun setupAndWait()
        fun destroy()
    }
}