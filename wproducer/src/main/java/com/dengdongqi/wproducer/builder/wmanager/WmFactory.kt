package com.dengdongqi.wproducer.builder.wmanager

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dengdongqi.wproducer.builder.pop.PopBuilder
import com.dengdongqi.wproducer.factory.AbsWindowFactory

/**
 * <pre>
 * Created by DengDongQi on 2021/4/27
 * WindowManager工厂类
 * </pre>
 */
class WmFactory : AbsWindowFactory() {
    override fun builder(context: Context) = get(context)

    override fun builder(activity: Activity) = get(activity)

    override fun builder(activity: FragmentActivity) = get(activity)

    override fun builder(view: View) = get(view.context)

    override fun builder(fragment: Fragment): WmBuilder {
        if(fragment.activity == null){
            throwFANullException()
        }
        return get(fragment.activity!!)
    }

    override fun builder(fragment: android.app.Fragment): WmBuilder {
        if(fragment.activity == null){
            throwFANullException()
        }
        return get(fragment.activity!!)
    }

    private fun get(context:Context?): WmBuilder {
        if(context == null){
            throwContextNullException()
        }
        return WmBuilder().builder(context!!)
    }

}