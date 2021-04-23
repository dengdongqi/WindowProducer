package com.dengdongqi.wproducer.builder.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.dengdongqi.wproducer.builder.OnShowDismissListener

/**
 * <pre>
 * Created by DengDongQi on 2019/7/25.
 * dialog建造者
 * </pre>
 */
class DialogBuilder : AbsDialogBuilder() {
    //上下文
    private lateinit var mContext:Context
    //具体dialog
    private lateinit var mDialog: MDialog
    //dialog附属属性
    private  var mWindow: Window? = null
    private var mLp: WindowManager.LayoutParams? = null

    override fun builder(mContext: Context): DialogBuilder {
        this.mContext = mContext
        mDialog = MDialog(mContext)
        return this
    }

    /**
     * 设置Dialog自定义布局
     * @param view Dialog自定义布局view
     * @return this@DialogBuilder
     * */
    override fun setContentView(contentView: View): DialogBuilder {
        mDialog.setContentView(contentView)
        mWindow = mDialog.window!!
        mLp = mWindow?.attributes
        return this
    }

    /**
     * 设置Dialog自定义布局
     * @param viewId Dialog自定义布局Id
     * @return this@DialogBuilder
     * */
    override fun setContentView(@LayoutRes viewLayoutId: Int): DialogBuilder {
        mDialog.setContentView(viewLayoutId)
        mWindow = mDialog.window
        mLp = mWindow?.attributes
        return this
    }

    /**
     * dialog弹出后会点击屏幕或物理返回键dialog是否消失
     * @param flag
     * @return this@DialogBuilder
     * */
    override fun setCancelable(flag: Boolean): DialogBuilder {
        mDialog.setCancelable(flag)
        return this
    }

    /**
     * 设置dialog Gravity
     * @param gravity  eg : Gravity.BOTTOM 底部显现dialog
     * @return this@DialogBuilder
     * */
    override fun setGravity(gravity: Int): DialogBuilder {
        if(mWindow == null) showThrow()
        mWindow!!.setGravity(gravity)
        return this
    }

    /**
     * 将此dialog的背景更改为自定义Drawable
     * @param drawable
     * @return this@DialogBuilder
     * */
    override fun setBackgroundDrawable(drawable: Drawable?): DialogBuilder {
        if(mWindow == null) showThrow()
        mWindow!!.setBackgroundDrawable(drawable)
        return this
    }

    /**
     * 设置背景是否模糊  已经模糊度
     * @param isDim 是否模糊
     * @param amount 模糊度 0.0f完全不暗，1.0f全暗
     * */
    override fun setdim(isDim: Boolean, amount: Float): DialogBuilder {
        if(mWindow == null) showThrow()
        val lp = mWindow!!.attributes
        if(isDim) {
            lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            lp.dimAmount = amount
            mWindow!!.attributes = lp
        }
        return this
    }

    /**
     * 设置此 dialog 进出动画
     * @param resId
     * @return this@DialogBuilder
     * */
    override fun setWindowAnimations(@StyleRes resId: Int): DialogBuilder {
        if(mWindow == null) showThrow()
        mWindow!!.setWindowAnimations(resId)
        return this
    }

    /**
     * 设置此dialog的内容的Padding
     * unit : px
     * @param left 左 padding
     * @param top
     * @param right
     * @param bottom
     * @return this@DialogBuilder
     * */
    override fun setContentPadding(left: Int, top: Int, right: Int, bottom: Int): DialogBuilder {
        if(mWindow == null) showThrow()
        mWindow!!.decorView.setPadding(left, top, right, bottom)
        return this
    }

    /**
     * 设置此窗口的内容的height
     * unit : px
     * @param height
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsHeight(height: Int): DialogBuilder {
        if(mLp == null) showThrow()
        mLp!!.height = height
        return this
    }

    /**
     * 设置此窗口的内容的width
     * unit : px
     * @param width
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsWidth(width: Int): DialogBuilder {
        if(mLp == null) showThrow()
        mLp!!.width = width
        return this
    }

    /**
     * 设置此窗口 x轴正位移
     * unit : px
     * @param x
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsX(x: Int): DialogBuilder {
        if(mLp == null) showThrow()
        mLp!!.x = x
        return this
    }

    /**
     * 设置此窗口 y轴正位移
     * unit : px
     * @param y
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsY(y: Int): DialogBuilder {
        if(mLp == null) showThrow()
        mLp!!.y = y
        return this
    }

    /**
     * 设置dialog show dismiss 监听
     * */
    override fun setOnShowDismissListener(onShowDismissListener: OnShowDismissListener): DialogBuilder {
        mDialog.setOnShowDismissListener(onShowDismissListener)
        return this
    }

    /**
     * 构建dialog
     * @return dialog
     * */
    override fun build(): Dialog {
        if(mWindow == null) showThrow()
        mWindow!!.attributes = mLp
        return mDialog
    }

    /**
     * 提示异常
     * */
    private fun showThrow(){
        throw IllegalStateException("You must call setContentView(...) first")
    }


}