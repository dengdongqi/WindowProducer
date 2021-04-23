package com.dengdongqi.wproducer.builder.pop

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.dengdongqi.wproducer.builder.OnShowDismissListener
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

/**
 * <pre>
 * Created by DengDongQi on 2021/4/21
 * PopupWindow建造者
 * </pre>
 */
class PopBuilder : AbsPopupWindowBuilder() {

    private lateinit var mPopupWindow:MPopupWindow
    private lateinit var mContext: Context
    private lateinit var mContentView: View
    //activity背景透明度
    private var mBgAlpha:Float = 0f
    //pop show dismiss 监听
    private var mOnShowDismissListener: OnShowDismissListener? = null

    /**
     * 获取构造者
     * */
    override fun builder(mContext: Context):PopBuilder{
        this.mContext = mContext
        mPopupWindow = MPopupWindow(mContext)
        return this
    }

    /**
     * 设置内容view
     * */
    override fun setContentView(contentView: View): PopBuilder {
        mContentView = contentView
        mPopupWindow.contentView = contentView
        return this
    }

    /**
     * 设置内容view
     * */
    override fun setContentView(viewLayoutId: Int): PopBuilder {
        mContentView = LayoutInflater.from(mContext).inflate(viewLayoutId,null)
        mPopupWindow.contentView = mContentView
        return this
    }

    /**
     * 设置宽
     * */
    override fun setWidth(width: Int): PopBuilder {
        mPopupWindow.width = width
        return this
    }

    /**
     * 设置高
     * */
    override fun setHeight(height: Int): PopBuilder {
        mPopupWindow.height = height
        return this
    }

    /**
     * 设置是否获取焦点
     * */
    override fun setFocusable(focusable: Boolean): PopBuilder {
        mPopupWindow.isFocusable = focusable
        return this
    }

    /**
     * 设置是否点击外部取消
     * */
    override fun setOutsideTouchable(touchable: Boolean): PopBuilder {
        mPopupWindow.isOutsideTouchable = touchable
        return this
    }

    /**
     * 设置背景资源
     * */
    override fun setBackgroundDrawable(background: Drawable): PopBuilder {
        mPopupWindow.setBackgroundDrawable(background)
        return this
    }

    /**
     * 设置输入模式
     * */
    override fun setInputMethodMode(mode: Int): PopBuilder {
        mPopupWindow.inputMethodMode = mode
        return this
    }

    /**
     * 设置软键盘模式
     * */
    override fun setSoftInputMode(mode: Int): PopBuilder {
        mPopupWindow.softInputMode = mode
        return this
    }

    /**
     * 设置动画
     * */
    override fun setAnimationStyle(animationStyle: Int): PopBuilder {
        mPopupWindow.animationStyle = animationStyle
        return this
    }

    /**
     * 设置监听pop show和dismiss
     * */
    override fun setOnShowDismissListener(onShowDismissListener: OnShowDismissListener): PopBuilder {
        if (mBgAlpha in 0f..1f){
           var proxyListener = proxyOnShowDismissListener(onShowDismissListener) as OnShowDismissListener
            mPopupWindow.setOnShowDismissListener(proxyListener)
        }else {
            mPopupWindow.setOnShowDismissListener(onShowDismissListener)
        }
        return this
    }

    /**
     * 设置关闭监听
     * */
    override fun setOnDismissListener(onDismissListener: PopupWindow.OnDismissListener): PopBuilder {
        mPopupWindow.setOnDismissListener(onDismissListener)
        //TODO 改为代理OnShowDismissListener接口管理bgAlpha
        /*hookDismissListener()*/
        return this
    }

    /**
     * 设置pop弹出时背景透明化 ,需popBuilder.setOnShowDismissListener()方法后才会起效
     * @param bgAlpha 0.0f - 1.0f
     * */
    override fun setBackgroundAlpha(bgAlpha: Float): PopBuilder {
        this.mBgAlpha = bgAlpha
        if (mBgAlpha in 0f..1f && this.mOnShowDismissListener!=null){
            var proxyListener = proxyOnShowDismissListener(mOnShowDismissListener!!) as OnShowDismissListener?
            if(proxyListener!=null) {
                mPopupWindow.setOnShowDismissListener(proxyListener)
            }
        }
        return this
    }

    /**
     * 构建popupWindow
     * */
    override fun build(): PopupWindow {
        return mPopupWindow
    }

    /**
     * 设置上下文所指activity阴影
     * @param mContext 上下文
     * @param bgAlpha 透明度
     * */
    private fun setActivityAlpha(mContext:Context,bgAlpha:Float){
        if(mContext is Activity && (bgAlpha in 0f..1.0f) ){
            val act = mContext
            val lp = act.window.attributes
            lp.alpha = bgAlpha
            act.window.attributes = lp
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }

//    /**
//     * hook pop 关闭监听
//     * */
//    private fun hookDismissListener() {
//        try {
//            val popClass = popupWindow.javaClass.superclass
//            val listnerField = popClass.getDeclaredField("mOnDismissListener")
//            if(listnerField!=null){
//                listnerField.isAccessible = true
//            }
//            val onDismissListener = listnerField.get(popupWindow) as PopupWindow.OnDismissListener?
//            if(onDismissListener!=null) {
//                listnerField.set(popupWindow, proxyListener(onDismissListener))
//            }
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
//    }
//
//    /**
//     * 动态代理onDismissListener,调用dismiss()之后恢复activity透明度
//     * @param listener
//     * */
//    private fun proxyListener(listener: PopupWindow.OnDismissListener): Any {
//        return Proxy.newProxyInstance(listener.javaClass.classLoader,
//            listener.javaClass.interfaces, InvocationHandler { proxy, method, args ->
//                val obj = if(args.isNullOrEmpty()){
//                    method.invoke(listener)
//                }else{
//                    method.invoke(listener,*args)
//                }
//                //dismiss之后
//                setActivityAlpha(mContext,1f)
//                return@InvocationHandler obj
//            })
//    }

    /**
     * 动态代理onShowDismissListener接口
     * show()之后设置透明度
     * dismiss 之后恢复
     * */
    private fun proxyOnShowDismissListener(listener: OnShowDismissListener): Any {
        return Proxy.newProxyInstance(listener.javaClass.classLoader
            ,listener.javaClass.interfaces, InvocationHandler { proxy, method, args ->
                if (method == null) return@InvocationHandler null
                val obj = if(args.isNullOrEmpty()){
                    method.invoke(listener)
                }else{
                    method.invoke(listener,*args)
                }
                if(method.name == "onShow"){
                    setActivityAlpha(mContext,mBgAlpha)
                }else if(method.name == "onDismiss"){
                    setActivityAlpha(mContext,1f)
                }
                return@InvocationHandler obj
            })
    }

}