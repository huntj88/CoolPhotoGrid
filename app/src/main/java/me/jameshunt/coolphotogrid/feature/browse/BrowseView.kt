package me.jameshunt.coolphotogrid.feature.browse

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.browse_layout.view.*
import me.jameshunt.coolphotogrid.di.page.PageComponent
import me.jameshunt.coolphotogrid.feature.recycler.AdapterContract
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by James on 10/5/2017.
 */
class BrowseView : ConstraintLayout, BrowseContract.View {

    @Inject
    override lateinit var presenter: BrowseContract.Presenter

    @Inject
    @field:[Named ("browse")]
    lateinit var adapter: AdapterContract.Adapter


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun inject(pageComponent: PageComponent) {
        pageComponent.inject(this)
        presenter.view = this

        browse_recycle.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        presenter.viewLoaded()

        AdapterContract.requestMoreWhenNecessary(browse_recycle, presenter, 4)
    }

    override fun refreshRecycler() {

        if (browse_recycle.adapter == null)
            browse_recycle.adapter = adapter as RecyclerView.Adapter<*>
        else
            (adapter as RecyclerView.Adapter<*>).notifyDataSetChanged()
    }

    override fun insertItemsRecycler(startIndex: Int, count: Int) {
        (adapter as RecyclerView.Adapter<*>).notifyItemRangeInserted(startIndex, count)
    }

    override fun showLoadingAnimation() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoadingAnimation() {
        progress_bar.visibility = View.GONE
    }
}