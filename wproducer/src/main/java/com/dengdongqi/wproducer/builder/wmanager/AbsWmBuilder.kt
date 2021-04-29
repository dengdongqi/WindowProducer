package com.dengdongqi.wproducer.builder.wmanager

import com.dengdongqi.wproducer.builder.AbsWindowBuilder
import com.dengdongqi.wproducer.builder.OnShowDismissListener
import com.dengdongqi.wproducer.builder.dialog.DialogBuilder
import com.dengdongqi.wproducer.builder.pop.PopBuilder

/**
 * <pre>
 * Created by DengDongQi on 2021/4/27
 * WindowManager构建者基类
 * </pre>
 */
abstract class AbsWmBuilder : AbsWindowBuilder() {

    //set lp type
    abstract fun setLpType(type:Int): WmBuilder
    //set lp flags
    abstract fun setLpFlags(flags:Int): WmBuilder
    //set lp gravity
    abstract fun setLpGravity(gravity:Int): WmBuilder
    //set lp Height
    abstract fun setLayoutParamsHeight(height: Int): WmBuilder
    //set lp Width
    abstract fun setLayoutParamsWidth(width: Int): WmBuilder
    //set lp X
    abstract fun setLayoutParamsX(x: Int): WmBuilder
    //set lp Y
    abstract fun setLayoutParamsY(y: Int): WmBuilder
    //set bg alpha
    abstract fun setBackgroundAlpha(bgAlpha: Float): WmBuilder
    //set show dis listener
    abstract fun setOnShowDismissListener(onShowDismissListener: OnShowDismissListener):WmBuilder
    //show
    abstract fun showFloatWindow(): WmBuilder
    //dismiss
    abstract fun dismissFloatWindow(): WmBuilder
}