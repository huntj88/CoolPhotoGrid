package me.jameshunt.coolphotogrid.feature.album

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

/**
 * Created by James on 10/8/2017.
 */
class AlbumView : ConstraintLayout, AlbumContract.View {

    override lateinit var presenter: AlbumContract.Presenter

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun updateRecycler() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingAnimation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingAnimation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}