package me.jameshunt.coolphotogrid.feature.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by James on 10/4/2017.
 */
abstract class Adapter(private val presenter: AdapterContract.Presenter<*>, private val viewHolderFactory: AdapterContract.ViewHolderFactory<*>): RecyclerView.Adapter<AdapterContract.ViewHolder>(), AdapterContract.Adapter {

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterContract.ViewHolder

    /*{
        return viewHolderFactory.createViewHolder(parent, presenter.getEnumForViewType(viewType))
    }*/

    override fun onBindViewHolder(holder: AdapterContract.ViewHolder, position: Int) {
        holder.bindData(presenter.getViewHolderData(position))
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }
}