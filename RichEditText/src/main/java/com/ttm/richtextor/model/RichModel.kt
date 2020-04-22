package com.ttm.richtextor.model

import android.text.Html
import android.text.Spanned
import android.text.TextUtils


open class RichModel {
    var content: String = ""
        private set
    var textColor: String = ""

    constructor(content: String, textColor: String) {
        this.content = content
        this.textColor = textColor
    }

    open fun isValid(): Boolean {
        return !TextUtils.isEmpty(content)
    }

    fun getContentHtml(): Spanned {
        return Html.fromHtml(
            String.format(
                String.format(
                    "<font color='%s'>${content}</font>",
                    textColor
                )
            )
        )
    }

    override fun toString(): String {
        return "RichModel{" +
                " insertContent='" + content + '\''.toString() +
                ", insertColor='" + textColor + '\''.toString() +
                '}'.toString()
    }
}
