package com.ttm.richtextor.util


import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager


object ScreenUtils {

    /**
     * sp转为px
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.density
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * px转为sp
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * dip转为PX
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val fontScale = context.resources.displayMetrics.density
        return (dipValue * fontScale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕的宽度px
     *
     * @param context 上下文
     * @return 屏幕宽px
     */
    fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()// 创建了一张白纸
        windowManager.defaultDisplay.getMetrics(outMetrics)// 给白纸设置宽高
        return outMetrics.widthPixels
    }

    /**
     * 获取屏幕的高度px
     *
     * @param context 上下文
     * @return 屏幕高px
     */
    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()// 创建了一张白纸
        windowManager.defaultDisplay.getMetrics(outMetrics)// 给白纸设置宽高
        return outMetrics.heightPixels
    }

}
