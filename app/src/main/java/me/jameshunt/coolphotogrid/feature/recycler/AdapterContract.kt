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

    interface Presenter {
        fun getItemViewType(position: Int): Int
        fun getItemCount(): Int
        fun getEnumForViewType(viewType: Int): ViewTypeEnum
        fun getViewHolderData(position: Int): ViewHolderData
    }

    abstract class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bindData(data: ViewHolderData)
    }


    interface ViewHolderFactory {
        fun createViewHolder(parent: ViewGroup, viewType: ViewTypeEnum): ViewHolder
    }

    interface ViewHolderData {

    }

    interface ViewTypeEnum {

    }
}