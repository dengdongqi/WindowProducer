package com.dengdongqi.wproducer.builder.pop

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.PopupWindow
import com.dengdongqi.wproducer.builder.OnShowDismissListener

/**
 * <pre>
 * Created by DengDongQi on 2021/4/22
 * PopupWindow 增加show() dismiss() 监听
 * </pre>
 */
class MPopupWindow : PopupWindow {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    constructor() {}
    constructor(contentView: View?) : super(contentView) {}
    constructor(width: Int, height: Int) : super(width, height) {}
    constructor(contentView: View?, width: Int, height: Int) : super(contentView, width, height) {}
    constructor(
        contentView: View?,
        width: Int,
        height: Int,
        focusable: Boolean
    ) : super(contentView, width, height, focusable) {
    }

    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        super.showAtLocation(parent, gravity, x, y)
        mOnShowDismissListener?.onShow()
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
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