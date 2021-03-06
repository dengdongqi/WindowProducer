package com.dengdongqi.wproducer.builder.dialog

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.dengdongqi.wproducer.factory.AbsWindowFactory

/**
 * <pre>
 * Created by DengDongQi on 2021/4/20
 * 负责创建DialogBuilder
 * </pre>
 */
class DialogFactory : AbsWindowFactory() {

    override fun builder(context: Context) = get(context)

    override fun builder(activity: Activity) = get(activity)

    override fun builder(activity: FragmentActivity) = get(activity)

    override fun builder(view: View) = get(view.context)

    override fun builder(fragment: Fragment): DialogBuilder {
        if(fragment.activity == null){
            throwFANullException()
        }
        return get(fragment.activity!!)
    }

    override fun builder(fragment: android.app.Fragment): DialogBuilder {
        if(fragment.activity == null){
           throwFANullException()
        }
        return get(fragment.activity!!)
    }

    private fun get(context:Context?): DialogBuilder{
        if(context == null){
            throwContextNullException()
        }
        return DialogBuilder().builder(context!!)
    }

}