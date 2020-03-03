package com.banketree.xinlanrichtextor

import com.ttm.richtextor.model.RichModel

class HideModel : RichModel {

    constructor(rule: String, content: String, textColor: String) : super(
        rule,
        content,
        textColor
    )

    constructor(contentRule: String, textColor: String) : super(contentRule, textColor)

    override fun isValid(): Boolean {
        return super.isValid()
    }

    override fun getContentRule(): String {
        return super.getContentRule().trim()
    }
}