package com.ttm.richtextor

import com.ttm.richtextor.model.RichModel

open class RichClickListener {
    var lastClickTime = 0L

    open fun onClick(richModel: RichModel) {
        lastClickTime = System.currentTimeMillis()
    }

    fun isClickHandled(): Boolean = (lastClickTime + 500) > System.currentTimeMillis()
}