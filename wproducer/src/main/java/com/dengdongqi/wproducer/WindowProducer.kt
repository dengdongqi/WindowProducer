package com.dengdongqi.wproducer

import com.dengdongqi.wproducer.builder.dialog.DialogFactory
import com.dengdongqi.wproducer.builder.pop.PopFactory
import com.dengdongqi.wproducer.builder.wmanager.WmFactory


/**
 * <pre>
 * Created by DengDongQi on 2021/4/21
 * 弹窗生产者
 * </pre>
 */
object WindowProducer{

    private var popFactory: PopFactory? = null

    private var dialogFactory: DialogFactory? = null

    private var windowManagerFactory:WmFactory? = null

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

    @JvmStatic
    fun windowManagerFactory():WmFactory{
        if(windowManagerFactory == null) {
            windowManagerFactory = WmFactory()
        }
        return windowManagerFactory!!
    }
}