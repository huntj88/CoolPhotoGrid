package me.jameshunt.coolphotogrid.feature.page.browse.viewHolder

import android.view.View
import kotlinx.android.synthetic.main.image_grid_two.view.*
import me.jameshunt.coolphotogrid.FrescoHelper
import me.jameshunt.coolphotogrid.feature.page.browse.viewHolder.util.GridData
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/6/2017.
 */
class ImageGridTwo(itemView: View): AdapterContract.ViewHolder(itemView) {

    override fun bindData(data: AdapterContract.ViewHolderData) {
        if(data !is GridData) {
            throw UnsupportedOperationException("wrong time of viewHolder for this data")
        }

        FrescoHelper.setImage(
                data.photos[0]?.urls?.thumb,
                itemView.left_image
        )

        FrescoHelper.setImage(
                data.photos[1]?.urls?.thumb,
                itemView.middle_image
        )

        FrescoHelper.setImage(
                data.photos[2]?.urls?.thumb,
                itemView.right_image
        )

    }


}