package com.dengdongqi.windowproducer.window.factory

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dengdongqi.windowproducer.window.builder.AbsWindowBuilder

/**
 * <pre>
 * Created by DengDongQi on 2021/4/21
 * 抽象弹窗工厂类
 * </pre>
 */
abstract class AbsWindowFactory {
    abstract fun builder(context: Context) : AbsWindowBuilder

    abstract fun builder(activity: Activity) : AbsWindowBuilder

    abstract fun builder(activity: FragmentActivity) : AbsWindowBuilder

    abstract fun builder(fragment: Fragment) : AbsWindowBuilder

    abstract fun builder(fragment: android.app.Fragment) : AbsWindowBuilder

    abstract fun builder(view: View) : AbsWindowBuilder
}