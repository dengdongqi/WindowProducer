package com.dengdongqi.wproducer.builder

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes

/**
 * <pre>
 * Created by DengDongQi on 2021/4/21
 * 弹窗抽象构造者父类
 * </pre>
 */
abstract class AbsWindowBuilder {
    abstract fun builder(mContext: Context):AbsWindowBuilder
    abstract fun setContentView(contentView: View): AbsWindowBuilder
    abstract fun setContentView(@LayoutRes viewLayoutId: Int): AbsWindowBuilder
    abstract fun build(): Any

    /**
     * 设置上下文所指activity阴影
     * @param mContext 上下文
     * @param bgAlpha 透明度
     * */
    fun setActivityAlpha(mContext:Context,bgAlpha:Float){
        if(mContext is Activity && (bgAlpha in 0f..1.0f) ){
            val act = mContext
            val lp = act.window.attributes
            lp.alpha = bgAlpha
            act.window.attributes = lp
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }


}