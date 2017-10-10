package me.jameshunt.coolphotogrid

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.renderscript.Allocation
import android.renderscript.ScriptIntrinsicBlur
import android.renderscript.RenderScript
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.renderscript.Element


/**
 * Created by James on 10/10/2017.
 */
class Blur {

    companion object {

        private val BLUR_RADIUS = 25f // 25 is maximum radius

        fun applyBlur(drawable: Drawable, context: Context, width: Int, height: Int): Drawable {
            val fromDrawable = drawableToBitmap(drawable, width, height)
            //val widthFromDrawable = Math.round(fromDrawable.width * 0.8f)
            //val heightFromDrawable = Math.round(fromDrawable.height * 0.8f)

            //val inBitmap = Bitmap.createScaledBitmap(fromDrawable, width, height, false)
            val inBitmap = fromDrawable
            val outBitmap = Bitmap.createBitmap(inBitmap)

            val renderScript = RenderScript.create(context)
            val blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))

            val `in` = Allocation.createFromBitmap(renderScript, inBitmap)
            val out = Allocation.createFromBitmap(renderScript, outBitmap)

            blur.setRadius(BLUR_RADIUS)
            blur.setInput(`in`)
            blur.forEach(out)

            out.copyTo(outBitmap)
            renderScript.destroy()

            return BitmapDrawable(context.resources, outBitmap)
        }

        private fun drawableToBitmap(drawable: Drawable, width: Int, height: Int): Bitmap {

            if (drawable is BitmapDrawable) {
                if (drawable.bitmap != null) {
                    return drawable.bitmap
                }
            }

            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
    }
}