package me.jameshunt.coolphotogrid.feature.activity

import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxNewPhotos

/**
 * Created by James on 10/5/2017.
 */
class ActivityModel(
        override val newPhotosEmitter: RxCommunicatorContract.Emitter<RxNewPhotos>,
        override val modelHolder: ModelHolder
) : ActivityContract.Model {

    override var isAlbumSelected: Boolean = false

    override fun canAlbumSlideUp(): Boolean {
        return modelHolder.browseModel?.currentApi != null
    }




}