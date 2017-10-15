package me.jameshunt.coolphotogrid

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest

/**
 * Created by James on 10/6/2017.
 */
class FrescoHelper {

    companion object {

        fun setImage(imageUrl: String?, view: SimpleDraweeView) {
            setImages(imageUrl, null, view)
        }

        fun setImages(imageUrl: String?, thumbUrl: String?, view: SimpleDraweeView) {
            if(imageUrl == null) return

            val builder = Fresco.newDraweeControllerBuilder()

            setImage(builder, imageUrl)
            setThumbnail(builder, thumbUrl)

            builder.oldController = view.controller
            val controller = builder.build()
            view.controller = controller
        }

        private fun setImage(build: PipelineDraweeControllerBuilder, imageUrl: String?): PipelineDraweeControllerBuilder {
            val imageUri = Uri.parse(imageUrl)
            build.imageRequest = ImageRequest.fromUri(imageUri)
            return build
        }

        private fun setThumbnail(build: PipelineDraweeControllerBuilder, thumbUrl: String?): PipelineDraweeControllerBuilder {

            if(thumbUrl != null) {
                val thumbUri = Uri.parse(thumbUrl)
                build.lowResImageRequest = ImageRequest.fromUri(thumbUri)
            }
            return build
        }
    }
}