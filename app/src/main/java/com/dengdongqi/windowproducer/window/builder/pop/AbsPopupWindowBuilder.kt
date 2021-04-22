package com.dengdongqi.windowproducer.window.builder.pop

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import com.dengdongqi.windowproducer.window.builder.AbsWindowBuilder
import com.dengdongqi.windowproducer.window.builder.OnShowDismissListener

/**
 * <pre>
 * Created by DengDongQi on 2021/4/21
 * PopupWindow抽象建造者
 * </pre>
 */
abstract class AbsPopupWindowBuilder : AbsWindowBuilder() {
    abstract fun setWidth(width: Int): PopBuilder
    abstract fun setHeight(height: Int): PopBuilder
    abstract fun setFocusable(focusable: Boolean): PopBuilder
    abstract fun setOutsideTouchable(touchable: Boolean): PopBuilder
    abstract fun setBackgroundDrawable(background: Drawable): PopBuilder
    abstract fun setInputMethodMode(mode: Int): PopBuilder
    abstract fun setSoftInputMode(mode: Int): PopBuilder
    abstract fun setAnimationStyle(animationStyle: Int): PopBuilder
    abstract fun setOnShowDismissListener(onShowDismissListener: OnShowDismissListener):PopBuilder
    abstract fun setOnDismissListener(onDismissListener: PopupWindow.OnDismissListener):PopBuilder
    abstract fun setBackgroundAlpha(bgAlpha: Float): PopBuilder
    abstract override fun build(): PopupWindow
}