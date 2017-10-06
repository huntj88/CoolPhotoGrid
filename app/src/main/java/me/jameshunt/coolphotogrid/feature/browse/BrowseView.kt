package me.jameshunt.coolphotogrid.feature.browse

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import me.jameshunt.coolphotogrid.di.page.PageComponent
import javax.inject.Inject

/**
 * Created by James on 10/5/2017.
 */
class BrowseView: ConstraintLayout, BrowseContract.View {

    @Inject
    override lateinit var presenter: BrowseContract.Presenter


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun inject(pageComponent: PageComponent) {
        pageComponent.inject(this)

        presenter.viewLoaded()
    }


}