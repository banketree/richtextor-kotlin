package com.ttm.richtextor

import android.content.Context
import android.graphics.Color
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import com.mabeijianxi.jianxiexpression.widget.ExpressionTextView
import com.ttm.richtextor.model.RichModel


class RichTextView : ExpressionTextView {
    var richClickListener: RichClickListener? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    fun initView() {
    }

    fun setTextToSpan(content: String, richModels: List<RichModel>) {
        text = getRichModelSpan(content, richModels)
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getRichModelSpan(content: String, richModels: List<RichModel>): Spannable {
        if (richModels == null || richModels.isEmpty())
            return SpannableString(content)
        val spannableString = SpannableString(content)
        for (richModel in richModels) {
            if (!richModel.isValid()) continue
            val startSelection = content.indexOf(richModel.content)
            val endSelection = startSelection + richModel.content.length
            if (startSelection == -1) continue
            makeClickSpan(spannableString, startSelection, endSelection, richModel)
        }
        return spannableString
    }

    //生成一个需要整体删除的Span
    private fun makeClickSpan(sps: Spannable, start: Int, end: Int, richModel: RichModel) {
        val what = ClickTopicSpan(richModel)
        sps.setSpan(what, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }

    fun isSpanClicked(): Boolean {
        richClickListener?.let {
            return it.isClickHandled()
        }
        return false
    }

    private inner class ClickTopicSpan(val model: RichModel) :
        ClickableSpan() {
        override fun onClick(widget: View) {
            richClickListener?.onClick(model)
        }

        override fun updateDrawState(tp: TextPaint) {
            if (model.textColor.isNotEmpty()) {
                /** 给文字染色  */
                tp.color = Color.parseColor(model.textColor)
            }
        }
    }
}