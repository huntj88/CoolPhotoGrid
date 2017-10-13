package me.jameshunt.coolphotogrid.feature.page.browse

import android.view.ViewGroup
import me.jameshunt.coolphotogrid.feature.page.browse.viewHolder.util.GridViewType
import me.jameshunt.coolphotogrid.feature.recycler.Adapter
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/6/2017.
 */
class CoolGridAdapter(private val presenter: AdapterContract.Presenter<GridViewType>, private val viewHolderFactory: AdapterContract.ViewHolderFactory<GridViewType>): Adapter(presenter, viewHolderFactory) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterContract.ViewHolder {
        return viewHolderFactory.createViewHolder(parent, presenter.getEnumForViewType(viewType))
    }
}