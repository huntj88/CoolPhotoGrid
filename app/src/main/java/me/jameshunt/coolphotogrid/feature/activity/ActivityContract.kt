package me.jameshunt.coolphotogrid.feature.activity

/**
 * Created by James on 10/5/2017.
 */
class ActivityContract {

    interface View {
        var presenter: Presenter
    }

    interface Model {

    }

    interface Presenter {
        val model: Model
    }
}