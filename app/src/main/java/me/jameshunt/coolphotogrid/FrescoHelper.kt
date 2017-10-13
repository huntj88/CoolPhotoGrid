package me.jameshunt.coolphotogrid

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest

/**
 * Created by James on 10/6/2017.
 */
class FrescoHelper {

    companion object {

        fun setImages(imageUrl: String?, thumbUrl: String?, view: SimpleDraweeView) {

            if(imageUrl == null) return

            val imageUri = Uri.parse(imageUrl)
            val thumbUri = Uri.parse(thumbUrl)

            setImageData(imageUri, thumbUri, view)
        }

        private fun setImageData(image: Uri, thumbnail: Uri, view: SimpleDraweeView) {
            val controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(image))
                    .setLowResImageRequest(ImageRequest.fromUri(thumbnail))
                    .setTapToRetryEnabled(true)
                    .setOldController(view.controller)
                    .build()

            view.controller = controller
        }
    }
}