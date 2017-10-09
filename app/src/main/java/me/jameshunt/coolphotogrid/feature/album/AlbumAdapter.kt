package me.jameshunt.coolphotogrid.feature.album

import android.view.ViewGroup
import me.jameshunt.coolphotogrid.feature.album.viewHolder.AlbumViewType
import me.jameshunt.coolphotogrid.feature.recycler.Adapter
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/9/2017.
 */
class AlbumAdapter(private val presenter: AdapterContract.Presenter<AlbumViewType>, private val viewHolderFactory: AdapterContract.ViewHolderFactory<AlbumViewType>): Adapter(presenter, viewHolderFactory) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterContract.ViewHolder {
        return viewHolderFactory.createViewHolder(parent, presenter.getEnumForViewType(viewType))
    }
}