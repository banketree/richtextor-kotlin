package com.ttm.richtextor

import com.ttm.richtextor.model.RichModel

interface IRichModelListener {
    fun onRemove(richModel: RichModel)
}