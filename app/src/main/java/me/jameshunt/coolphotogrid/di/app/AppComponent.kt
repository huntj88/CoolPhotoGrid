package me.jameshunt.coolphotogrid.di.app

import dagger.Component
import javax.inject.Singleton

/**
 * Created by James on 10/4/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

}