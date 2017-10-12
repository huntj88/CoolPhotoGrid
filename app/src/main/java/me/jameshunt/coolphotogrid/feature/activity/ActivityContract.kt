package me.jameshunt.coolphotogrid.feature.activity

import me.jameshunt.coolphotogrid.feature.activity.slide.SlideOnTouch

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
        val modelHolder: ModelHolder

        var isAlbumSelected: Boolean
        fun canAlbumSlideUp(): Boolean
    }

    interface Presenter: SlideOnTouch.PresenterForSlide {
        val model: Model
        var view: View

        fun setupAndWait()
        fun destroy()
    }
}