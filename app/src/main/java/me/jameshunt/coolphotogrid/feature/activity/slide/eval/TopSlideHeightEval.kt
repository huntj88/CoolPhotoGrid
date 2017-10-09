package me.jameshunt.coolphotogrid.feature.activity.slide.eval

import android.view.View

/**
 * Created by James on 10/4/2017.
 */
class TopSlideHeightEval(private val top: View, private val bottom: View, private val directionDown: Boolean, closedHeight: Int): SlideEval(closedHeight) {

    override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
        val topParams = top.layoutParams
        val bottomParams = bottom.layoutParams

        topParams.height = super.evaluate(fraction, startValue, endValue)!!

        val startHeight = Math.min(bottomParams.height, closedHeight)

        if(directionDown)
            bottomParams.height = super.evaluate(fraction, startHeight, 0)
        else
            bottomParams.height = closedHeight


        top.layoutParams = topParams

        return topParams.height
    }
}