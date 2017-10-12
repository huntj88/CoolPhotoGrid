package me.jameshunt.coolphotogrid.feature.album.viewHolder

import android.view.View
import kotlinx.android.synthetic.main.album_view_holder.view.*
import me.jameshunt.coolphotogrid.FrescoHelper
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData
import me.jameshunt.coolphotogrid.repo.realm.RealmCollection

/**
 * Created by James on 10/9/2017.
 */
class AlbumViewHolder(itemView: View): AdapterContract.ViewHolder(itemView) {

    lateinit var album: RealmCollection
    lateinit var albumClickedEmitter: RxCommunicatorContract.Emitter<RxAlbumData>

    override fun bindData(data: AdapterContract.ViewHolderData) {
        if(data !is SelectAlbumData) {
            throw UnsupportedOperationException("wrong time of viewHolder for this data")
        }

        album = data.collection

        FrescoHelper.setImages(album.coverPhoto?.urls?.regular, itemView.album_image)
        itemView.album_name.text = album.title

        val numPhotosText = album.numPhotos.toString() + " photos"

        itemView.num_photos.text = numPhotosText

        itemView.setOnClickListener {
            albumClickedEmitter.emitter.onNext(RxAlbumData(album))
        }

    }
}