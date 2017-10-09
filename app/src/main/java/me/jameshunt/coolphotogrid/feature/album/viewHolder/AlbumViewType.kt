package me.jameshunt.coolphotogrid.feature.album.viewHolder

import android.view.View
import me.jameshunt.coolphotogrid.R
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/9/2017.
 */
enum class AlbumViewType(override val viewID: Int, override val layout: Int): AdapterContract.ViewTypeEnum {

    ALBUM(0, R.layout.album_view_holder) {
        override fun extraViewOperations(view: View) {

        }
    }
}