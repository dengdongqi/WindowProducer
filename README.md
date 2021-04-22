# WindowProducer
构建者+抽象工厂实现 Dialog, PopupWindow 快捷生成  
除 setOnShowDismissListener() 接口外其他都是原始 Dialog 和 PopupWindow 原生接口  
WindowProducer 只负责选择具体工厂,具体工厂只负责构建最终具体产品


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

### 扩展更多原生接口
在AbsDialogBuilder,AbsPopupWindowBuilder声明其他接口,并由DialogBuilder,PopBuilder实现具体逻辑即可
