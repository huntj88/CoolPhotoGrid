package me.jameshunt.coolphotogrid.di.page

import dagger.Component
import me.jameshunt.coolphotogrid.di.activity.ActivityComponent
import me.jameshunt.coolphotogrid.feature.browse.BrowseView

/**
 * Created by James on 10/5/2017.
 */

@PageScope
@Component(modules = arrayOf(PageModule::class), dependencies = arrayOf(ActivityComponent::class))
interface PageComponent {

    fun inject(browseView: BrowseView)
}