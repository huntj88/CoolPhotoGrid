package me.jameshunt.coolphotogrid.feature.album

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import kotlinx.android.synthetic.main.album_layout.view.*
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
        dividerDecoration.setDrawable(ContextCompat.getDrawable(context,R.drawable.album_divider))

        album_recycle.addItemDecoration(dividerDecoration)

        presenter.viewLoaded()
    }


    override fun updateRecycler() {
        if (album_recycle.adapter == null)
            album_recycle.adapter = adapter as RecyclerView.Adapter<*>
        else
            (adapter as RecyclerView.Adapter<*>).notifyDataSetChanged()
    }

    override fun showLoadingAnimation() {

    }

    override fun hideLoadingAnimation() {

    }


}