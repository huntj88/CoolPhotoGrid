package me.jameshunt.coolphotogrid.feature.album

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.album_layout.view.*
import kotlinx.android.synthetic.main.top_handle.view.*
import me.jameshunt.coolphotogrid.R
import me.jameshunt.coolphotogrid.di.page.PageComponent
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by James on 10/8/2017.
 */
class AlbumView : ConstraintLayout, AlbumContract.View {

    @Inject
    override lateinit var presenter: AlbumContract.Presenter

    @Inject
    @field:[Named("album")]
    lateinit var adapter: AdapterContract.Adapter

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun inject(pageComponent: PageComponent) {
        pageComponent.inject(this)
        presenter.view = this


        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        album_recycle.layoutManager = layoutManager

        val dividerDecoration = DividerItemDecoration(context, layoutManager.orientation)
        dividerDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.album_divider))

        album_recycle.addItemDecoration(dividerDecoration)

        presenter.viewLoaded()
        requestMoreWhenNecessary()

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        //handle_shadow.setImageDrawable(Blur.applyBlur(ContextCompat.getDrawable(context,R.drawable.album_handle_shadow),context, handle_shadow.width, handle_shadow.height))
    }


    override fun refreshRecycler() {
        if (album_recycle.adapter == null)
            album_recycle.adapter = adapter as RecyclerView.Adapter<*>
        else
            (adapter as RecyclerView.Adapter<*>).notifyDataSetChanged()
    }

    override fun insertItemsRecycler(startIndex: Int, count: Int) {
        (adapter as RecyclerView.Adapter<*>).notifyItemRangeInserted(startIndex, count)
    }

    override fun showLoadingAnimation() {

    }

    override fun hideLoadingAnimation() {

    }

    override fun setAlbumInfo(albumName: String, numPhotos: Int) {
        no_album_text.visibility = View.GONE

        album_name_handle.visibility = View.VISIBLE
        num_photos_handle.visibility = View.VISIBLE
        top_grabber.visibility = View.VISIBLE
        bottom_grabber.visibility = View.VISIBLE

        album_name_handle.text = albumName
        num_photos_handle.text = numPhotos.toString()


    }

    private fun requestMoreWhenNecessary() {

        album_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val cellHeight = recyclerView.findViewHolderForLayoutPosition(lastVisible)?.itemView?.height ?: 0


                if (cellHeight != 0) {
                    val heightToReload = ((layoutManager.itemCount - 10) * cellHeight)

                    if (lastVisible * cellHeight > heightToReload)
                        presenter.requestMore()
                }

            }

        })
    }


}