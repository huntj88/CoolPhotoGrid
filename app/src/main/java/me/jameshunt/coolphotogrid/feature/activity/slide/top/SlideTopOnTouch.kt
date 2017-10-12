package me.jameshunt.coolphotogrid.feature.activity.slide.top

import android.animation.ValueAnimator
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import me.jameshunt.coolphotogrid.feature.activity.slide.SlideOnTouch
import me.jameshunt.coolphotogrid.feature.activity.slide.SlideOnTouch.PlaceToSnap
import me.jameshunt.coolphotogrid.feature.activity.slide.SlideOnTouch.SlideValues.getTopHandleHeightForTopTouch

/**
 * Created by James on 10/11/2017.
 */
class SlideTopOnTouch(private val topView: View, private val bottomView: View, private val presenterForSlide: SlideOnTouch.PresenterForSlide) : SlideOnTouch {

    private var startHeight = 0
    private var lastY = 0
    private var yStart = 0
    private var snapToLocation: PlaceToSnap = PlaceToSnap.BOTTOM

    private var screenHeight = 0

    override fun actionDown(event: MotionEvent) {
        screenHeight = (topView.parent as FrameLayout).height

        startHeight = topView.height
        lastY = event.rawY.toInt()
        yStart = event.rawY.toInt()
    }

    override fun actionMove(event: MotionEvent) {
        val totalHeightDiff = event.rawY - yStart

        val topParams = topView.layoutParams
        topParams.height = Math.max(startHeight + totalHeightDiff.toInt(), 0)
        topView.layoutParams = topParams


        snapToLocation = getSnapToLocation(event, lastY, screenHeight)

        lastY = event.rawY.toInt()


        val bottomParams = bottomView.layoutParams
        bottomParams.height = getBottomHeight()

        bottomView.layoutParams = bottomParams

    }

    private fun getBottomHeight(): Int {

        // use BOTTOM for placeToSnap just so animation is smooth.
        // otherwise it will switch between BOTTOM and TOP and cause a little jump
        val topHandleHeight: Int = getTopHandleHeightForTopTouch(presenterForSlide, PlaceToSnap.BOTTOM)


        val percent = (lastY - topHandleHeight) / screenHeight.toFloat()
        val heightRequest = Math.round(SlideOnTouch.bottomHandleHeight - (percent * SlideOnTouch.bottomHandleHeight))
        return Math.min(heightRequest, SlideOnTouch.bottomHandleHeight)
    }


    override fun actionUp(event: MotionEvent) {
        animate()
    }

    private fun animate() {
        val startHeight = topView.height

        val topHandleHeight: Int = getTopHandleHeightForTopTouch(presenterForSlide, snapToLocation)

        val endHeight: Int = when (snapToLocation) {
            PlaceToSnap.BOTTOM -> {
                screenHeight + topHandleHeight
            }
            PlaceToSnap.TOP -> {
                topHandleHeight
            }
        }

        val animation = ValueAnimator.ofObject(
                TopSlideHeightEval(topView = topView, bottomView = bottomView, presenterForSlide = presenterForSlide, snapToLocation = snapToLocation),
                startHeight,
                endHeight).setDuration(300)

        animation.start()
    }

    override fun slideOpposite() {
        snapToLocation = when(snapToLocation){
            PlaceToSnap.BOTTOM -> PlaceToSnap.TOP
            PlaceToSnap.TOP -> PlaceToSnap.BOTTOM
        }

        animate()
    }


}