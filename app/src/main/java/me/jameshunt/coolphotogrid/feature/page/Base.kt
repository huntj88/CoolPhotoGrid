package me.jameshunt.coolphotogrid.feature.page

/**
 * Created by James on 10/12/2017.
 */
interface Base {

    interface View {
        fun destroy()
    }

    interface Presenter {
        fun destroy()
    }
}