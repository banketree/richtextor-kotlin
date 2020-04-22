package com.banketree.xinlanrichtextor

import com.ttm.richtextor.model.RichModel

class HideModel : RichModel {

    constructor(contentRule: String, textColor: String) : super(contentRule, textColor)

    override fun isValid(): Boolean {
        return super.isValid()
    }
}