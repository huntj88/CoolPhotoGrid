package me.jameshunt.coolphotogrid.feature.browse.viewHolder

import android.view.View
import kotlinx.android.synthetic.main.image_grid_one.view.*
import me.jameshunt.coolphotogrid.FrescoHelper
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridData
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/6/2017.
 */
class ImageGridOne(itemView: View): AdapterContract.ViewHolder(itemView) {

    override fun bindData(data: AdapterContract.ViewHolderData) {
        if(data !is GridData) {
            throw UnsupportedOperationException("wrong time of viewHolder for this data")
        }


        FrescoHelper.setImages(data.photos[0]?.urls?.small, itemView.left_image)
        FrescoHelper.setImages(data.photos[1]?.urls?.small, itemView.right_image)

    }
}


/*


 val uri = Uri.parse(photo.thumbnailUrl)

        val controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(itemView.my_image_view.controller)
                .build()

        itemView.my_image_view.controller = controller


 */