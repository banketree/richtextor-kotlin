package com.ttm.richtextor.model

import android.text.Html
import android.text.Spanned
import android.text.TextUtils


open class RichModel {
    var rule: String = ""
    var content: String = ""
        private set
    var textColor: String = ""

    constructor(rule: String, content: String, textColor: String) {
        this.rule = rule
        this.content = content
        this.textColor = textColor
    }

    constructor(contentRule: String,textColor: String) {
        var contentRuleTemp = contentRule.trim()
        this.rule = contentRuleTemp.substring(0, 1)
        this.content = contentRuleTemp.substring(1, contentRuleTemp.length - 1)
        this.textColor = textColor
    }

    open fun isValid(): Boolean {
        return !TextUtils.isEmpty(content)
    }

    open fun getContentRule(): String {
        return "$rule$content$rule "
    }


    fun getContentHtml(): Spanned {
        return Html.fromHtml(
            String.format(
                String.format(
                    "<font color='%s'>${getContentRule()}</font>",
                    textColor
                )
            )
        )
    }

    override fun toString(): String {
        return "RichModel{" +
                "insertRule='" + rule + '\''.toString() +
                ", insertContent='" + getContentRule() + '\''.toString() +
                ", insertColor='" + textColor + '\''.toString() +
                '}'.toString()
    }
}
