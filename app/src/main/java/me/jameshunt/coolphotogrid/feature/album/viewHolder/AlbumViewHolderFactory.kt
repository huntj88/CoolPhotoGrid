package me.jameshunt.coolphotogrid.feature.album.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/9/2017.
 */
class AlbumViewHolderFactory: AdapterContract.ViewHolderFactory<AlbumViewType> {

    override fun createViewHolder(parent: ViewGroup, viewType: AlbumViewType): AdapterContract.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(viewType.layout, parent, false)
        viewType.extraViewOperations(view)

        return AlbumViewHolder(view)
    }
}