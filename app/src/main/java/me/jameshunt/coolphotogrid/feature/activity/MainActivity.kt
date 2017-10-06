package me.jameshunt.coolphotogrid.feature.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.jameshunt.coolphotogrid.R
import me.jameshunt.coolphotogrid.di.activity.Injector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityContract.View {

    @Inject
    override lateinit var presenter: ActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Injector(this).inject()
    }
}
