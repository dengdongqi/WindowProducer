package com.dengdongqi.windowproducer.window.builder

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.dengdongqi.windowproducer.window.builder.pop.PopBuilder

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

}