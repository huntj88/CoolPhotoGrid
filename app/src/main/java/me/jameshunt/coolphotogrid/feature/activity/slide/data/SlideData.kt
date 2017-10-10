package me.jameshunt.coolphotogrid.feature.activity.slide.data

import android.view.View
import me.jameshunt.coolphotogrid.Dimensions
import me.jameshunt.coolphotogrid.feature.activity.slide.eval.SlideEval

/**
 * Created by James on 10/4/2017.
 */
abstract class SlideData(
        var yStart: Float = 0f,
        var lastY: Float = 0f,
        var mainView: View,  //the one you can touch
        var secondaryView: View //the one that needs to move out of the way
) {

    abstract var location: SlideLocation
    val closedHeight = Dimensions.dpToPx(28).toInt()
    var currentHeight: Int = closedHeight
    
    abstract fun fixOverlap()

    abstract fun followFinger(rawY: Int)

    abstract fun convertDirection(direction: Boolean): Boolean

    abstract fun getEvaluator(direction: Boolean): SlideEval

    enum class SlideLocation {
        TOP,
        BOTTOM
    }
}