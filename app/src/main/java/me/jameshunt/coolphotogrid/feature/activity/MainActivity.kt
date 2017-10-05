package me.jameshunt.coolphotogrid.feature.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.jameshunt.coolphotogrid.R

class MainActivity : AppCompatActivity(), ActivityContract.View {

    override lateinit var presenter: ActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
