package com.dengdongqi.windowproducer.window.builder.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.dengdongqi.windowproducer.window.builder.OnShowDismissListener

/**
 * <pre>
 * Created by DengDongQi on 2021/4/22
 *
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