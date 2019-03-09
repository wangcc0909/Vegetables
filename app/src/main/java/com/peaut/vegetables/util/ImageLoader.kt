package com.peaut.vegetables.util

/**
 * @author peaut
 * @package  com.peaut.vegetables.util
 * @fileName ImageLoader
 * @date on  2019/2/28  20:20
 */
object ImageLoader {

    /**
     * 加载网络图片
     * @param context
     * @param url
     * @param imageView
     */
    /*fun loadUrlImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).placeholder(R.drawable.icon_default)
                .error(R.drawable.icon_default).into(
                        object : SimpleTarget<GlideDrawable>() {
                            override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                                imageView.setImageDrawable(resource)
                            }
                        }
                )

    }*/

    /**
     * 加载网络图片
     * @param activity
     * @param url
     * @param imageView
     */
    /*@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun loadUrlImage(activity: Activity, url: String, imageView: ImageView) {
        if(!activity.isDestroyed){
            Glide.with(activity).load(url).placeholder(R.drawable.icon_default)
                    .error(R.drawable.icon_default).into(
                            object : SimpleTarget<GlideDrawable>() {
                                override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                                    imageView.setImageDrawable(resource)
                                }
                            }
                    )
        }
    }*/



}