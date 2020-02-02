package com.ttm.richtextor

import android.content.Context
import android.text.*
import android.text.style.MetricAffectingSpan
import android.util.AttributeSet
import android.util.Log

import androidx.appcompat.widget.AppCompatEditText
import com.ttm.richtextor.model.RichModel
import java.util.ArrayList


class RichEditText : AppCompatEditText {

    //获取用户Id列表
    val userIdString: String
        get() {
            val spans = text!!.getSpans(0, text!!.length, AtTextSpan::class.java)
            val builder = StringBuilder()
            for (myTextSpan in spans) {
                val realText = text!!.toString()
                    .substring(text!!.getSpanStart(myTextSpan), text!!.getSpanEnd(myTextSpan))
                val showText = myTextSpan.model.getContent()
                if (realText == showText) {
                    builder.append(myTextSpan.model.rule).append(",")
                }
            }
            if (!TextUtils.isEmpty(builder.toString())) {
                builder.deleteCharAt(builder.length - 1)
            }
            return builder.toString()
        }


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

    /**
     * 监听光标的位置,若光标处于话题内容中间则移动光标到话题结束位置
     */
    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        var selStart2 = selStart
        var selEnd2 = selEnd

        getSpaned()?.let {
            for (itemSpan in it) {
                val spanStart = text!!.getSpanStart(itemSpan)
                val spanEnd = text!!.getSpanEnd(itemSpan)

                if (selStart == selEnd) { //同一光标
                    if (selStart in (spanStart + 1) until spanEnd) {// 若光标处于话题内容中间则移动光标到话题结束位置
                        setSelection(spanEnd)
                        return
                    }
                    continue
                }

                if (selStart in (spanStart + 1) until spanEnd) {
                    selStart2 = spanStart
                }

                if (selEnd in (spanStart + 1) until spanEnd) {
                    selEnd2 = spanEnd
                }
            }
        }

        if (selStart != selStart2 || selEnd != selEnd2) {
            setSelection(selStart2, selEnd2)
            return
        }

        super.onSelectionChanged(selStart2, selEnd2)
    }

    /**
     * 添加一个块,在文字的后面添加
     *
     * @param model 插入的数据
     */
    fun addSpan(model: RichModel) {
        if (!model.isValid()) {
            return
        }
        //避免插入相同的数据
        if (hasSpaned(model)) {
            return
        }

        val htmlText = model.getContentHtml()
        text!!.insert(selectionStart, htmlText)
        val sps = SpannableString(text)

        val start =
            selectionEnd - htmlText.toString().length - if (TextUtils.isEmpty(model.getContent())) 1 else 0
        val end = selectionEnd
        makeSpan(sps, start, end, model)
        setText(sps)
        setSelection(end)
    }

    //生成一个需要整体删除的Span
    private fun makeSpan(sps: Spannable, start: Int, end: Int, richModel: RichModel) {
        val what = AtTextSpan(richModel)
        sps.setSpan(what, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        //向前删除一个字符，@后的内容必须大于一个字符，可以在后面加一个空格
        getText()?.let {
            if (lengthBefore == 1 && lengthAfter == 0) {
                val spans = it.getSpans(0, it.length, AtTextSpan::class.java)
                for (itemSpan in spans) {
                    if (it.getSpanEnd(itemSpan) == start && !text.toString().endsWith(
                            itemSpan.model.getContentRule()
                        )
                    ) {
                        val spanStart = it.getSpanStart(itemSpan)
                        it.delete(
                            it.getSpanStart(itemSpan),
                            it.getSpanEnd(itemSpan)
                        )
                        post {
                            setSelection(spanStart)
                        }

                        break
                    }
                }
            }
        }
    }

    private fun getSpaned(): Array<out AtTextSpan>? {
        return text?.getSpans(0, text!!.length, AtTextSpan::class.java)
    }

    private fun hasSpaned(model: RichModel): Boolean {
        getSpaned()?.let {
            for (itemSpan in it) {
                if (TextUtils.equals(model.getContentRule(), itemSpan.model.getContentRule())) {
                    Log.i("", "")
                    return true
                }
            }
        }
        return false
    }

    private inner class AtTextSpan(val model: RichModel) : MetricAffectingSpan() {
        override fun updateMeasureState(p: TextPaint) {
        }

        override fun updateDrawState(tp: TextPaint) {
        }
    }
}