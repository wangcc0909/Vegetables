package com.peaut.vegetables.util

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader

/**
 * @author peaut
 * @package  com.peaut.vegetables.util
 * @fileName GlideImageLoader
 * @date on  2019/2/28  20:16
 */
class GlideImageLoader: ImageLoader(){
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        /*imageView?.let {
            context?.let { it1 -> loadU }
        }*/
        context?.let { GlideApp.with(it).load(path).into(imageView!!) }
    }
}