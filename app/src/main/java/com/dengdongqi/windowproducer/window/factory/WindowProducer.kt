package com.dengdongqi.windowproducer.window.factory

import com.dengdongqi.windowproducer.window.builder.dialog.DialogFactory
import com.dengdongqi.windowproducer.window.builder.pop.PopFactory


/**
 * <pre>
 * Created by DengDongQi on 2021/4/21
 * 弹窗生产者
 * </pre>
 */
object WindowProducer{

    private var popFactory: PopFactory? = null

    private var dialogFactory: DialogFactory? = null

    @JvmStatic
    fun popFactory() : PopFactory{
        if(popFactory == null) {
            popFactory = PopFactory()
        }
        return popFactory!!
    }

    @JvmStatic
    fun dialogFactory():DialogFactory{
        if(dialogFactory == null) {
            dialogFactory = DialogFactory()
        }
        return dialogFactory!!
    }
}