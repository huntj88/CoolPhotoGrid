package me.jameshunt.coolphotogrid.feature.page.album.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import me.jameshunt.coolphotogrid.feature.rx.RxCommunicatorContract
import me.jameshunt.coolphotogrid.feature.rx.data.RxAlbumData

/**
 * Created by James on 10/9/2017.
 */
class AlbumViewHolderFactory(private val albumClickedEmitter: RxCommunicatorContract.Emitter<RxAlbumData>): AdapterContract.ViewHolderFactory<AlbumViewType> {

    override fun createViewHolder(parent: ViewGroup, viewType: AlbumViewType): AdapterContract.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(viewType.layout, parent, false)
        viewType.extraViewOperations(view)

        val albumViewHolder =  AlbumViewHolder(view)
        albumViewHolder.albumClickedEmitter = albumClickedEmitter

        return albumViewHolder
    }
}