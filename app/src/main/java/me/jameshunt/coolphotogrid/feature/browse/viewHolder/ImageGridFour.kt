package me.jameshunt.coolphotogrid.feature.browse.viewHolder

import android.view.View
import kotlinx.android.synthetic.main.image_grid_four.view.*
import me.jameshunt.coolphotogrid.FrescoHelper
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.util.GridData
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import timber.log.Timber

/**
 * Created by James on 10/7/2017.
 */

class ImageGridFour(itemView: View): AdapterContract.ViewHolder(itemView) {

    override fun bindData(data: AdapterContract.ViewHolderData) {
        if(data !is GridData) {
            throw UnsupportedOperationException("wrong time of viewHolder for this data")
        }

        Timber.i("fourth: "+ data.photos[0]?.id)

        FrescoHelper.setImages(data.photos[0]?.urls?.thumb, itemView.top_left_image)
        FrescoHelper.setImages(data.photos[1]?.urls?.thumb, itemView.bottom_left_image)
        FrescoHelper.setImages(data.photos[2]?.urls?.regular, itemView.right_image)

    }


}