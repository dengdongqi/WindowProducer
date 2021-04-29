package com.dengdongqi.wproducer.factory

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dengdongqi.wproducer.builder.AbsWindowBuilder

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

    //fragment getActivity() == null 时抛出异常
    fun throwFANullException(){
        throw NullPointerException(
            "You cannot start a builder on a Fragment where getActivity() "
                    + "returns null (which usually occurs when getActivity() is called before the Fragment "
                    + "is attached or after the Fragment is destroyed)."
        )
    }

    //context == null 时抛出异常
    fun throwContextNullException(){
        throw NullPointerException("You cannot start a builder on a null Context")
    }
}