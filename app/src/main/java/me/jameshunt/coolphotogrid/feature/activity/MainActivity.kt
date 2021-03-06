package me.jameshunt.coolphotogrid.feature.activity

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.album_layout.*
import kotlinx.android.synthetic.main.browse_layout.*
import kotlinx.android.synthetic.main.viewer_layout.*
import me.jameshunt.coolphotogrid.Dimensions
import me.jameshunt.coolphotogrid.R
import me.jameshunt.coolphotogrid.di.activity.Injector
import me.jameshunt.coolphotogrid.feature.activity.slide.SlideOnTouch
import me.jameshunt.coolphotogrid.feature.activity.slide.bottom.SlideBottomOnTouch
import me.jameshunt.coolphotogrid.feature.activity.slide.top.SlideTopOnTouch
import me.jameshunt.coolphotogrid.feature.page.album.AlbumView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityContract.View {

    @Inject
    override lateinit var presenter: ActivityContract.Presenter

    private var topSlideTouch: SlideOnTouch? = null
    private lateinit var bottomSlideTouch: SlideOnTouch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val injector = Injector(this)
        injector.inject()

        presenter.view = this
        presenter.setupAndWait()

        setupSlide() //album view added programmatically when setting up slide
        injector.injectAlbumView()

    }

    override fun showBrowse() {
        topSlideTouch?.slideOpposite()
    }

    override fun showViewer() {

    }

    private fun setupSlide() {
        addTopSlide()
        handleSlideTop()
        handleSlideBottom()
    }

    private fun addTopSlide() {
        val topSlide = layoutInflater.inflate(R.layout.album_layout, null, false) as AlbumView

        val screenHeight = resources.displayMetrics.heightPixels - Dimensions.dpToPx(24)

        val height = (screenHeight + Dimensions.dpToPx(60)).toInt()
        val width = resources.displayMetrics.widthPixels

        val params: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(width, height)
        topSlide.layoutParams = params

        main_frame.addView(topSlide)
    }

    private fun handleSlideTop() {
        topSlideTouch = SlideTopOnTouch(album_view, viewer_view, presenter)
        setupSlideTouchListener(slide_handle_top, topSlideTouch!!)
    }

    private fun handleSlideBottom() {
        bottomSlideTouch = SlideBottomOnTouch(viewer_view, album_view)
        setupSlideTouchListener(slide_handle_bottom, bottomSlideTouch)
    }

    private fun setupSlideTouchListener(view: View, onTouch: SlideOnTouch) {
        view.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    onTouch.actionDown(event)
                }

                MotionEvent.ACTION_MOVE -> {
                    onTouch.actionMove(event)
                }

                MotionEvent.ACTION_CANCEL,
                MotionEvent.ACTION_UP -> {
                    onTouch.actionUp(event)
                }
            }

            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.destroy()
        album_view.destroy()
        browse_view.destroy()

    }
}
