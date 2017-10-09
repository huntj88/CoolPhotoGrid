package me.jameshunt.coolphotogrid.feature.album

import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract

/**
 * Created by James on 10/8/2017.
 */
class AlbumPresenter: AlbumContract.Presenter {

    override lateinit var view: AlbumContract.View

    override fun viewLoaded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEnumForViewType(viewType: Int): AlbumViewType {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewHolderData(position: Int): AdapterContract.ViewHolderData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}