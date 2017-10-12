package me.jameshunt.coolphotogrid.feature.recycler

import android.support.v7.widget.LinearLayoutManager
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

    interface Presenter<out Types : ViewTypeEnum> {
        fun getItemViewType(position: Int): Int
        fun getItemCount(): Int
        fun getEnumForViewType(viewType: Int): Types
        fun getViewHolderData(position: Int): ViewHolderData

        fun requestMore()
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindData(data: ViewHolderData)
    }


    // dagger needs this to not be "in"
    interface ViewHolderFactory<Types : ViewTypeEnum> {
        fun createViewHolder(parent: ViewGroup, viewType: Types): ViewHolder
    }

    interface ViewHolderData {

    }

    interface ViewTypeEnum {
        val viewID: Int
        val layout: Int

        fun extraViewOperations(view: View)
    }

    companion object {
        fun requestMoreWhenNecessary(recyclerView: RecyclerView, presenter: Presenter<*>, numCellsFromBottomBeforeReload: Int) {

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val cellHeight = recyclerView.findViewHolderForLayoutPosition(lastVisible)?.itemView?.height ?: 0


                    if (cellHeight != 0) {

                        //when it gets to around last "numCellsFromBottomBeforeReload" viewHolders, reload more
                        //approximation, due to viewHolders potentially being different height

                        val heightToReload = ((layoutManager.itemCount - numCellsFromBottomBeforeReload) * cellHeight)

                        if (lastVisible * cellHeight > heightToReload) {
                            recyclerView.post({ presenter.requestMore() })
                        }
                    }

                }

            })
        }
    }
}