package me.jameshunt.coolphotogrid.feature.album.viewHolder

import android.view.View
import kotlinx.android.synthetic.main.album_view_holder.view.*
import me.jameshunt.coolphotogrid.FrescoHelper
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/9/2017.
 */
class AlbumViewHolder(itemView: View): AdapterContract.ViewHolder(itemView) {

    override fun bindData(data: AdapterContract.ViewHolderData) {
        if(data !is SelectAlbumData) {
            throw UnsupportedOperationException("wrong time of viewHolder for this data")
        }

        FrescoHelper.setImages(data.collection.coverPhoto?.urls?.regular, itemView.album_image)
        itemView.album_name.text = data.collection.title

        val numPhotosText = data.collection.numPhotos.toString() + " photos"

        itemView.num_photos.text = numPhotosText

    }
}