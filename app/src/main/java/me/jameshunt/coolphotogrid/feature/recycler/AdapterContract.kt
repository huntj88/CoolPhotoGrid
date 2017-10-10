package me.jameshunt.coolphotogrid.feature.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by James on 10/4/2017.
 */


interface AdapterContract {

    interface Adapter {

    }

    interface AdapterView {
        fun refreshRecycler()
        fun insertItemsRecycler(startIndex: Int, count: Int)
        fun showLoadingAnimation()
        fun hideLoadingAnimation()
    }

    interface Presenter<out Types: ViewTypeEnum> {
        fun getItemViewType(position: Int): Int
        fun getItemCount(): Int
        fun getEnumForViewType(viewType: Int): Types
        fun getViewHolderData(position: Int): ViewHolderData
    }

    abstract class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bindData(data: ViewHolderData)
    }


    // dagger needs this to not be "in"
    interface ViewHolderFactory<Types: ViewTypeEnum> {
        fun createViewHolder(parent: ViewGroup, viewType: Types): ViewHolder
    }

    interface ViewHolderData {

    }

    interface ViewTypeEnum {
        val viewID: Int
        val layout: Int

        fun extraViewOperations(view: View)
    }
}