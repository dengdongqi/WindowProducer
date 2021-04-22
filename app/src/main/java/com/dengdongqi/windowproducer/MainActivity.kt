package com.dengdongqi.windowproducer

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.PopupWindow
import com.dengdongqi.windowproducer.window.builder.OnShowDismissListener
import com.dengdongqi.windowproducer.window.factory.WindowProducer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dialog = WindowProducer
            .dialogFactory()
            .builder(this)
            .setContentView(R.layout.activity_main)
            .setCancelable(true)
            .setLayoutParamsWidth(800)
            .setLayoutParamsHeight(400)
            .setBackgroundDrawable(ColorDrawable(Color.parseColor("#ff00ff")))
            .setOnShowDismissListener(object :OnShowDismissListener{
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
    }
}