package me.jameshunt.coolphotogrid.feature.activity

/**
 * Created by James on 10/5/2017.
 */
class ActivityModel(private val modelHolder: ModelHolder) : ActivityContract.Model {

    override var isAlbumSelected: Boolean = false

    override fun canAlbumSlideUp(): Boolean {
        return modelHolder.browseModel?.currentApi != null
    }




}