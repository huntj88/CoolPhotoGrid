package me.jameshunt.coolphotogrid.di.activity

import kotlinx.android.synthetic.main.album_layout.*
import kotlinx.android.synthetic.main.browse_layout.*
import me.jameshunt.coolphotogrid.CoolApplication
import me.jameshunt.coolphotogrid.di.page.AlbumModule
import me.jameshunt.coolphotogrid.di.page.BrowseModule
import me.jameshunt.coolphotogrid.di.page.DaggerPageComponent
import me.jameshunt.coolphotogrid.feature.activity.MainActivity

/**
 * Created by James on 10/5/2017.
 */
class Injector(private val mainActivity: MainActivity) {

    fun inject() {
        val appComponent = (mainActivity.applicationContext as CoolApplication).appComponent
        val activityComponent = DaggerActivityComponent.builder().appComponent(appComponent).activityModule(ActivityModule()).build()
        activityComponent.inject(mainActivity)

        injectLayouts(activityComponent)
    }


    private fun injectLayouts(activityComponent: ActivityComponent) {
        val pageComponent = DaggerPageComponent
                .builder()
                .activityComponent(activityComponent)
                .browseModule(BrowseModule())
                .albumModule(AlbumModule())
                .build()

        mainActivity.browse_view.inject(pageComponent)
        mainActivity.album_view.inject(pageComponent)
    }
}