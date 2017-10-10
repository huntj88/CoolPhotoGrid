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

                                    //first launch get categories too
                                    //when we get those, get some images from each album.
                                    //after that emit data to album Screen

                                    //when started get all the new photo from the api. when they have been saved in realm, emit new data to browse screen





                                    // above comments are is probably not the final version
                                    // on first launch get categories, and when you get those tell album to update its album data
                                    // any future launches tell album people to get json album photo json immediately

                                    // when app started emit RxNewPhotos, and let the factory in browse build the data and return it




    //above comments are stupid.


    override fun canAlbumSlideUp(): Boolean {
        return modelHolder.browseModel?.currentApi != null
    }




}