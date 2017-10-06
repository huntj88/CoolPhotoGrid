package me.jameshunt.coolphotogrid.feature.rx.data

import me.jameshunt.coolphotogrid.feature.rx.RxData

/**
 * Created by James on 10/5/2017.
 */
data class RxFavoritesChanged(

        val added: List<String>,
        val removed: List<String>

) : RxData {
}


/**
 *
 * added contains a list of the photo id's that were added to favorites
 * removed is list of removed photo id's
 */