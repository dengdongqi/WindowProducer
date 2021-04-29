package com.dengdongqi.wproducer.builder.pop

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dengdongqi.wproducer.factory.AbsWindowFactory

/**
 * <pre>
 * Created by DengDongQi on 2021/4/21
 * 负责创建PopBuilder
 * </pre>
 */
class PopFactory : AbsWindowFactory() {

    override fun builder(context: Context) = get(context)

    override fun builder(activity: Activity) = get(activity)

    override fun builder(activity: FragmentActivity) = get(activity)

    override fun builder(view: View) = get(view.context)

    override fun builder(fragment: Fragment): PopBuilder {
        if(fragment.activity == null){
            throwFANullException()
        }
        return get(fragment.activity!!)
    }

    override fun builder(fragment: android.app.Fragment): PopBuilder {
        if(fragment.activity == null){
            throwFANullException()
        }
        return get(fragment.activity!!)
    }

    private fun get(context:Context?): PopBuilder{
        if(context == null){
            throwContextNullException()
        }
        return PopBuilder().builder(context!!)
    }


}