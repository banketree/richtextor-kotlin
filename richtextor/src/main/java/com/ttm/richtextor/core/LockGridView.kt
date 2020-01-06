package com.ttm.richtextor.core

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView

class LockGridView : GridView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec =
            MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}
