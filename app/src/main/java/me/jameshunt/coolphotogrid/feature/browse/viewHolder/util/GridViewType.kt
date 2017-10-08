package me.jameshunt.coolphotogrid.feature.browse.viewHolder.util

import android.view.View
import com.facebook.drawee.view.SimpleDraweeView
import me.jameshunt.coolphotogrid.R
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/4/2017.
 */
enum class GridViewType(override val viewID: Int, override val layout: Int): AdapterContract.ViewTypeEnum {

    ONE(0, R.layout.image_grid_one) {
        override fun extraViewOperations(view: View) {

            val widthOfScreen = view.context.resources.displayMetrics.widthPixels

            val left = view.findViewById<SimpleDraweeView>(R.id.left_image)
            val right = view.findViewById<SimpleDraweeView>(R.id.right_image)

            setHeight(left, widthOfScreen /2)
            setHeight(right, widthOfScreen /2)

        }
    },
    TWO(1, R.layout.image_grid_two) {
        override fun extraViewOperations(view: View) {
            val widthOfScreen = view.context.resources.displayMetrics.widthPixels

            val left = view.findViewById<SimpleDraweeView>(R.id.left_image)
            val middle = view.findViewById<SimpleDraweeView>(R.id.middle_image)
            val right = view.findViewById<SimpleDraweeView>(R.id.right_image)

            setHeight(left, widthOfScreen /3)
            setHeight(middle, widthOfScreen /3)
            setHeight(right, widthOfScreen /3)
        }
    },
    THREE(2, R.layout.image_grid_three) {
        override fun extraViewOperations(view: View) {
            val widthOfScreen = view.context.resources.displayMetrics.widthPixels
            setHeight(view, widthOfScreen / 3 * 2)
        }
    },
    FOUR(3, R.layout.image_grid_four) {
        override fun extraViewOperations(view: View) {
            val widthOfScreen = view.context.resources.displayMetrics.widthPixels
            setHeight(view, widthOfScreen / 3 * 2)
        }
    };


    protected fun setHeight(view: View, width: Int) {
        val params = view.layoutParams
        params.height = width
        view.layoutParams = params
    }


    companion object {
        private val map = GridViewType.values().associateBy(GridViewType::viewID)
        fun fromInt(type: Int) = map[type]
    }


}