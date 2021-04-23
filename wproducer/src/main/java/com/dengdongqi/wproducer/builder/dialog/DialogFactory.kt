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
            throw NullPointerException(
                "You cannot start a load on a not yet attached View or a Fragment where getActivity() "
                        + "returns null (which usually occurs when getActivity() is called before the Fragment "
                        + "is attached or after the Fragment is destroyed)."
            )
        }
        return get(fragment.activity!!)
    }

    override fun builder(fragment: android.app.Fragment): DialogBuilder {
        if(fragment.activity == null){
            throw NullPointerException(
                "You cannot start a load on a not yet attached View or a Fragment where getActivity() "
                        + "returns null (which usually occurs when getActivity() is called before the Fragment "
                        + "is attached or after the Fragment is destroyed)."
            )
        }
        return get(fragment.activity!!)
    }

    private fun get(context:Context): DialogBuilder{
        /*if(context == null){
            throw IllegalArgumentException("You cannot start a load on a null Context")
        }*/
        return DialogBuilder().builder(context)
    }

}