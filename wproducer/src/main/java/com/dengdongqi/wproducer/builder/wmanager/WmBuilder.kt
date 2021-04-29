package com.dengdongqi.wproducer.builder.wmanager

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Point
import android.os.Build
import android.view.*
import com.dengdongqi.wproducer.builder.OnShowDismissListener
import com.dengdongqi.wproducer.builder.pop.PopBuilder

/**
 * <pre>
 * Created by DengDongQi on 2021/4/27
 * WindowManager构建者
 * </pre>
 */
class WmBuilder : AbsWmBuilder() {
    //上下文
    private lateinit var mContext: Context
    //show dismiss 监听
    private var mOnShowDismissListener: OnShowDismissListener? = null

    //系统窗口管理
    lateinit var mWindowManager: WindowManager
    //悬浮VIew
    lateinit var mFloatView: View
    //布局属性
    lateinit var layoutParams: WindowManager.LayoutParams
    //activity背景透明度
    private var mBgAlpha:Float = -1f


    override fun builder(mContext: Context): WmBuilder {
        this.mContext = mContext
        mWindowManager = mContext.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        layoutParams = WindowManager.LayoutParams()
        //设置悬浮窗口宽高WRAP_CONTENT
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        return this
    }

    override fun setContentView(contentView: View): WmBuilder {
        mFloatView = contentView
        return this
    }

    override fun setContentView(viewLayoutId: Int): WmBuilder {
        mFloatView = LayoutInflater.from(mContext).inflate(viewLayoutId,null)
        return this
    }

    override fun setLpType(type: Int): WmBuilder {
        layoutParams.type = type
        return this
    }

    override fun setLpFlags(flags:Int): WmBuilder {
        layoutParams.flags = flags
        return this
    }

    override fun setLpGravity(gravity: Int): WmBuilder {
        layoutParams.gravity = gravity
        //setTouchSliding()
        return this
    }

    override fun setLayoutParamsHeight(height: Int): WmBuilder {
        layoutParams.height = height
        return this
    }

    override fun setLayoutParamsWidth(width: Int): WmBuilder {
        layoutParams.width = width
        return this
    }

    override fun setLayoutParamsX(x: Int): WmBuilder {
        layoutParams.x = x
        return this
    }

    override fun setLayoutParamsY(y: Int): WmBuilder {
        layoutParams.y = y
        return this
    }

   /* @SuppressLint("ClickableViewAccessibility")
    override fun setTouchSliding(): WmBuilder {
        if (!::mFloatView.isInitialized) {
            throw IllegalStateException("You must call setContentView(...) first")
        }
        mFloatView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    //getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
                    layoutParams.x = (event.rawX - mFloatView.measuredWidth.toFloat()/2).toInt()
                    layoutParams.y = *//*getScreenHeight() -*//* (event.rawY *//*- mFloatView.measuredHeight.toFloat()*//*).toInt()
                    //刷新
                    mWindowManager.updateViewLayout(mFloatView, layoutParams)
                }
                MotionEvent.ACTION_UP -> {
                }
            }
            return@setOnTouchListener false
        }
        return this
    }*/

    override fun setBackgroundAlpha(bgAlpha: Float): WmBuilder {
        this.mBgAlpha = bgAlpha
        return this
    }

    override fun setOnShowDismissListener(onShowDismissListener: OnShowDismissListener): WmBuilder {
        return this
    }


    override fun build(): WmBuilder {
        return this
    }

    override fun showFloatWindow(): WmBuilder {
        if (!::mFloatView.isInitialized) {
            throw IllegalStateException("You must call setContentView(...) first")
        }
        mWindowManager.addView(mFloatView, layoutParams)
        if (mBgAlpha in 0f..1f) {
            setActivityAlpha(mContext, mBgAlpha)
        }
        mOnShowDismissListener?.onShow()
        return this
    }

    override fun dismissFloatWindow(): WmBuilder{
        if(!::mFloatView.isInitialized){
            throw IllegalStateException("You must call setContentView(...) first")
        }
        mWindowManager.removeView(mFloatView)
        if (mBgAlpha in 0f..1f) {
            setActivityAlpha(mContext, 1f)
        }
        mOnShowDismissListener?.onDismiss()
        return this
    }

//    /**
//     * 获取屏幕宽度
//     */
//    private fun getScreenWidth(): Int {
//        val point = Point()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            mWindowManager.defaultDisplay.getRealSize(point)
//        } else {
//            mWindowManager.defaultDisplay.getSize(point)
//        }
//        return point.x
//    }
//
//    /**
//     *获取屏幕高度
//     */
//    private fun getScreenHeight(): Int {
//        val point = Point()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            mWindowManager.defaultDisplay.getRealSize(point)
//        } else {
//            mWindowManager.defaultDisplay.getSize(point)
//        }
//        return point.y
//    }



}