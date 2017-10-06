package me.jameshunt.coolphotogrid.feature.browse.viewHolder.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.ImageGridOne
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.ImageGridThree
import me.jameshunt.coolphotogrid.feature.browse.viewHolder.ImageGridTwo
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/6/2017.
 */
class GridViewHolderFactory: AdapterContract.ViewHolderFactory<GridViewType> {

    override fun createViewHolder(parent: ViewGroup, viewType: GridViewType): AdapterContract.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(viewType.layout, parent, false)
        viewType.extraViewOperations(view)

        return makeCorrectViewHolder(view,viewType)
    }


    private fun makeCorrectViewHolder(view: View,viewType: GridViewType): AdapterContract.ViewHolder {

        return when(viewType){
            GridViewType.ONE -> ImageGridOne(view)
            GridViewType.TWO -> ImageGridTwo(view)
            GridViewType.THREE -> ImageGridThree(view)
        }
    }
}