package com.dengdongqi.windowproducer

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dengdongqi.wproducer.WindowProducer
import com.dengdongqi.wproducer.builder.OnShowDismissListener
import com.dengdongqi.wproducer.builder.wmanager.WmBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region dialog
        val dialog: Dialog= WindowProducer
            .dialogFactory()
            .builder(this)
            .setContentView(R.layout.activity_main)
            .setCancelable(true)
            .setdim(true,0.5f)
            .setLayoutParamsWidth(800)
            .setLayoutParamsHeight(400)
            .setBackgroundDrawable(ColorDrawable(Color.parseColor("#ff00ff")))
            .setOnShowDismissListener(object : OnShowDismissListener {
                override fun onShow() {
                    Log.e("DDQ","dialog show")
                }

                override fun onDismiss() {
                    Log.e("DDQ","dialog dismiss")
                }
            })
            .build()

        dialog.findViewById<Button>(R.id.button).setOnClickListener {
            dialog.dismiss()
        }

        button.setOnClickListener {
            dialog.show()
        }
        //endregion

        //region pop
        val pop = WindowProducer.popFactory()
            .builder(this)
            .setContentView(R.layout.activity_main)
            .setWidth(800)
            .setHeight(400)
            .setFocusable(true)
            .setOutsideTouchable(true)
            .setBackgroundDrawable(ColorDrawable(Color.parseColor("#ff00ff")))
            .setBackgroundAlpha(0.5f)
            .setOnShowDismissListener(object :OnShowDismissListener{
                override fun onShow() {
                    Log.e("DDQ","PopupWindow show")
                }

                override fun onDismiss() {
                    Log.e("DDQ","PopupWindow dismiss")
                }
            })
            .build()

        pop.contentView.findViewById<Button>(R.id.button).setOnClickListener {
            pop.dismiss()
        }

        button2.setOnClickListener {
            pop.showAsDropDown(button,0,0, Gravity.CENTER)
        }
        //endregion

        //region WindowManager
        val wmBuilder: WmBuilder = WindowProducer
            .windowManagerFactory()
            .builder(this)
            .setContentView(R.layout.activity_main)
            .setLpType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
            .setLpFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            .setLpGravity(Gravity.TOP or Gravity.START)
            .build()

        wmBuilder.mFloatView.background = ColorDrawable(Color.parseColor("#ff0000"))

        var x = 0f
        var y = 0f
        wmBuilder.mFloatView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    x = event.x
                    y = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    wmBuilder.layoutParams.x = (event.rawX - x).toInt()
                    wmBuilder.layoutParams.y = (event.rawY - y).toInt()
                    //刷新
                    wmBuilder.mWindowManager.updateViewLayout(wmBuilder.mFloatView, wmBuilder.layoutParams)
                }
            }
            return@setOnTouchListener true
        }

        button3.setOnClickListener {
            wmBuilder.showFloatWindow()
        }

        wmBuilder.mFloatView.findViewById<Button>(R.id.button).setOnClickListener {
            wmBuilder.dismissFloatWindow()
        }
        //endregion

    }
}