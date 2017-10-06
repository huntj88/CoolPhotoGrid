package me.jameshunt.coolphotogrid.di.activity

import me.jameshunt.coolphotogrid.CoolApplication
import me.jameshunt.coolphotogrid.feature.activity.MainActivity

/**
 * Created by James on 10/5/2017.
 */
class Injector(val mainActivity: MainActivity) {

    fun inject() {
        val appComponent = (mainActivity.applicationContext as CoolApplication).appComponent
        val activityComponent = DaggerActivityComponent.builder().appComponent(appComponent).activityModule(ActivityModule()).build()
        activityComponent.inject(mainActivity)
    }
}