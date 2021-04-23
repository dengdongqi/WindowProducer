package com.dengdongqi.wproducer

import com.dengdongqi.wproducer.builder.dialog.DialogFactory
import com.dengdongqi.wproducer.builder.pop.PopFactory


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