package com.dengdongqi.wproducer.builder.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.dengdongqi.wproducer.builder.OnShowDismissListener

/**
 * <pre>
 * Created by DengDongQi on 2021/4/22
 * Dialog 增加show() dismiss() 监听
 * </pre>
 */
class MDialog :Dialog{
    constructor(context: Context) : super(context) {}
    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}
    protected constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener) {

    }

    override fun show() {
        super.show()
        mOnShowDismissListener?.onShow()
    }

    override fun dismiss() {
        super.dismiss()
        mOnShowDismissListener?.onDismiss()
    }

    private var mOnShowDismissListener: OnShowDismissListener? = null

    fun setOnShowDismissListener(onShowListener: OnShowDismissListener){
        this.mOnShowDismissListener = onShowListener
    }

}