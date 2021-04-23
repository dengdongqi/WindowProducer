package com.dengdongqi.wproducer.builder.dialog

import android.app.Dialog
import android.graphics.drawable.Drawable
import androidx.annotation.StyleRes
import com.dengdongqi.wproducer.builder.AbsWindowBuilder
import com.dengdongqi.wproducer.builder.OnShowDismissListener

/**
 * <pre>
 * Created by DengDongQi on 2019/7/25.
 * dialog抽象建造者
 * <pre>
 */
abstract class AbsDialogBuilder: AbsWindowBuilder() {
    abstract fun setCancelable(flag: Boolean): DialogBuilder
    abstract fun setGravity(gravity: Int): DialogBuilder
    abstract fun setBackgroundDrawable(drawable: Drawable?): DialogBuilder
    abstract fun setdim(isDim:Boolean,amount:Float): DialogBuilder
    abstract fun setWindowAnimations(@StyleRes resId: Int): DialogBuilder
    abstract fun setContentPadding(left: Int, top: Int, right: Int, bottom: Int): DialogBuilder
    abstract fun setLayoutParamsHeight(height: Int): DialogBuilder
    abstract fun setLayoutParamsWidth(width: Int): DialogBuilder
    abstract fun setLayoutParamsX(x: Int): DialogBuilder
    abstract fun setLayoutParamsY(y: Int): DialogBuilder
    abstract fun setOnShowDismissListener(onShowDismissListener: OnShowDismissListener): DialogBuilder
    abstract override fun build(): Dialog
}