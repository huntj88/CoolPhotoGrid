package me.jameshunt.coolphotogrid.feature.activity.slide.data

import android.view.View
import android.widget.FrameLayout
import me.jameshunt.coolphotogrid.feature.activity.slide.eval.SlideEval
import me.jameshunt.coolphotogrid.feature.activity.slide.eval.TopSlideHeightEval

/**
 * Created by James on 10/4/2017.
 */

class SlideDataTop(mainView: View, secondaryView: View): SlideData(mainView = mainView, secondaryView = secondaryView) {

    override var location: SlideLocation = SlideLocation.BOTTOM

    override fun fixOverlap() {

        val screenHeight = (mainView.parent as FrameLayout).height

        var bottomYMain = mainView.y.toInt() + mainView.layoutParams.height

        if(bottomYMain > screenHeight)
            bottomYMain = screenHeight

        val secondaryHeight = screenHeight - bottomYMain

        if(secondaryView.y < bottomYMain) {
            secondaryView.layoutParams.height = secondaryHeight
        } else {

            if(bottomYMain > screenHeight - closedHeight) {
                secondaryView.layoutParams.height = secondaryHeight
            } else {
                secondaryView.layoutParams.height = closedHeight
            }

        }
    }

    override fun followFinger(rawY: Int) {
        val totalHeightDiff = rawY - yStart

        val screenHeight = (mainView.parent as FrameLayout).height

        val params = mainView.layoutParams
        val newHeight = Math.min(currentHeight + totalHeightDiff.toInt(), screenHeight)
        params.height = newHeight
        mainView.layoutParams = params
    }

    override fun convertDirection(direction: Boolean): Boolean {
        return !direction
    }

    override fun getEvaluator(direction: Boolean): SlideEval {
        return TopSlideHeightEval(mainView, secondaryView, direction, closedHeight)
    }
}