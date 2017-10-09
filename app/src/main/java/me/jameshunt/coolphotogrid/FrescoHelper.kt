package me.jameshunt.coolphotogrid

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by James on 10/6/2017.
 */
class FrescoHelper {

    companion object {

        fun setImages(url: String?, view: SimpleDraweeView) {

            if(url == null) return

            val leftUriThumb = Uri.parse(url)
            setImageData(leftUriThumb, view)
        }

        private fun setImageData(thumbnail: Uri, view: SimpleDraweeView) {
            val controller = Fresco.newDraweeControllerBuilder()
                    .setUri(thumbnail)
                    .setTapToRetryEnabled(true)
                    .setOldController(view.controller)
                    .build()

            view.controller = controller
        }
    }
}