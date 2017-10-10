package me.jameshunt.coolphotogrid.feature.activity

import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.album_layout.*
import kotlinx.android.synthetic.main.viewer_layout.*
import me.jameshunt.coolphotogrid.R
import me.jameshunt.coolphotogrid.di.activity.Injector
import me.jameshunt.coolphotogrid.feature.activity.slide.data.SlideData
import me.jameshunt.coolphotogrid.feature.activity.slide.data.SlideDataBottom
import me.jameshunt.coolphotogrid.feature.activity.slide.data.SlideDataTop
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityContract.View {

    private lateinit var bottomSlideData: SlideData
    private lateinit var topSlideData: SlideData

    private var directionDown = true


    @Inject
    override lateinit var presenter: ActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Injector(this).inject()
        presenter.view = this
        presenter.setupAndWait()
        setupSlide()
    }

    override fun showBrowse() {
        directionDown = false
        animateSlide(topSlideData)
    }

    override fun showViewer() {

    }

    private fun setupSlide() {

        bottomSlideData = SlideDataBottom(mainView = viewer_layout, secondaryView = album_view)
        topSlideData = SlideDataTop(mainView = album_view, secondaryView = viewer_layout)

        slide_handle_bottom.setOnTouchListener { _, motionEvent ->
            touchListener(motionEvent, bottomSlideData)
            true
        }

        slide_handle_top.setOnTouchListener { _, motionEvent ->
            touchListener(motionEvent, topSlideData)
            true
        }

    }

    private fun touchListener(motionEvent: MotionEvent, slideData: SlideData) {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {

                slideData.yStart = motionEvent.rawY
                slideData.lastY = motionEvent.rawY
                slideData.currentHeight = slideData.mainView.height

            }
            MotionEvent.ACTION_MOVE -> {

                slideData.followFinger(motionEvent.rawY.toInt())
                slideData.fixOverlap()

                directionDown = motionEvent.rawY > slideData.lastY
                slideData.lastY = motionEvent.rawY

            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {

                animateSlide(slideData)
            }
            else -> {}
        }
    }

    private fun animateSlide(slideData: SlideData) {
        val startHeight = slideData.mainView.height
        val endHeight = if (slideData.convertDirection(directionDown)) slideData.closedHeight else main_frame.height

        val animation = ValueAnimator.ofObject(
                slideData.getEvaluator(directionDown),
                startHeight,
                endHeight).setDuration(300)

        animation.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
