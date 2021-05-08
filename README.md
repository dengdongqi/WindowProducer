# WindowProducer
构建者+抽象工厂实现 Dialog, PopupWindow ,WindowManager快捷生成  
除 setOnShowDismissListener() 接口外其他都是原生接口  

## 使用
* build.gradle构建文件中添加JitPack
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

* ..app/build.gradle 添加依赖关系
```
	dependencies {
            ......
	        implementation 'com.github.dengdongqi:WindowProducer:Tag'
	}
```


## UML结构图
![UML结构图](https://github.com/dengdongqi/WindowProducer/blob/master/windowproducer.png?raw=true)

## 构建dialog
```
val dialog: Dialog = WindowProducer
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
            
//view
val bt = dialog.findViewById<Button>(R.id.XX)

//event
bt.setOnClickListener {
    
}

//show dismiss
dialog.show()
dialog.dismiss()
            
```

## 构建popupWindow
```
val pop: PopupWindow = WindowProducer
        .popFactory()
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

//view
val bt = pop.contentView.findViewById<Button>(R.id.XX)

//event
bt.setOnClickListener {
    
}

//show dismiss
pop.showAsDropDown(view,0,0, Gravity.CENTER)
pop.dismiss()
```

## 构建WindowManager悬浮窗
```
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
```

### 扩展更多
在AbsDialogBuilder,AbsPopupWindowBuilder,AbsWmBuilder声明其他接口,并由DialogBuilder,PopBuilder,WmBuilder实现具体逻辑即可
