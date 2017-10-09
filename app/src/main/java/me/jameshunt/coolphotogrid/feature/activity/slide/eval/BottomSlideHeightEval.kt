package me.jameshunt.coolphotogrid.feature.activity.slide.eval

import android.util.Log
import android.view.View

/**
 * Created by James on 10/4/2017.
 */
class BottomSlideHeightEval(private val bottom: View, private val top: View, private val directionDown: Boolean, closedHeight: Int): SlideEval(closedHeight) {

    override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
        val bottomParams = bottom.layoutParams
        val topParams = top.layoutParams
        bottomParams.height = super.evaluate(fraction, startValue, endValue)!!

        if(!directionDown)
            topParams.height = super.evaluate(fraction, closedHeight, 0)
        else
            topParams.height = closedHeight

        bottom.layoutParams = bottomParams
        top.layoutParams = topParams


        Log.d("slideHeightEval","top height: " + top.layoutParams.height+"    top y: "+ top.y)
        Log.d("slideHeightEval","bottom height: " + bottom.layoutParams.height+"    bottom y: "+ bottom.y)

        return bottomParams.height
    }
}