package com.ttm.richtextor

import android.content.Context
import android.text.*
import android.text.method.KeyListener
import android.text.style.MetricAffectingSpan
import android.util.AttributeSet
import com.mabeijianxi.jianxiexpression.widget.ExpressionEditText
import com.ttm.richtextor.model.RichModel
import java.util.ArrayList


class RichEditText : ExpressionEditText {
    var iRichModelListener: IRichModelListener? = null
    private var recordKeyListener: KeyListener? = null

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

        getSpanedList()?.let {
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

    fun setTextToSpan(content: String, richModels: List<RichModel>) {
        setText(content)
        if (TextUtils.isEmpty(content) || richModels.isEmpty() || text == null) return
        for (richModel in richModels) {
            if (!richModel.isValid()) continue
            val startSelection = content.indexOf(richModel.getContentRule())
            val endSelection = startSelection + richModel.getContentRule().length
            if (startSelection == -1) continue

            text!!.delete(startSelection, endSelection)
            val htmlText = richModel.getContentHtml()

            text!!.insert(startSelection, htmlText)
            val sps = SpannableString(text)

            val start =
                endSelection - htmlText.toString().length
            val end = endSelection
            makeSpan(sps, start, end, richModel)
            setText(sps)
        }
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
            selectionEnd - htmlText.toString().length
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
                    val spanStart = it.getSpanStart(itemSpan)
                    val spanEnd = it.getSpanEnd(itemSpan)
                    val realEnd = spanStart + itemSpan.model.getContentRule().length
                    if (spanEnd == start && realEnd > start && !text.toString().endsWith(
                            itemSpan.model.getContentRule()
                        )
                    ) {
                        it.delete(
                            spanStart,
                            spanEnd
                        )
                        post {
                            setSelection(spanStart)
                        }
                        iRichModelListener?.onRemove(itemSpan.model)
                        break
                    }
                }
            }
        }
    }

    private fun getSpanedList(): Array<out AtTextSpan>? {
        return text?.getSpans(0, text!!.length, AtTextSpan::class.java)
    }

    private fun hasSpaned(model: RichModel): Boolean {
        getSpanedList()?.let {
            for (itemSpan in it) {
                if (TextUtils.equals(model.getContentRule(), itemSpan.model.getContentRule())) {
                    return true
                }
            }
        }
        return false
    }

    fun getRichModelList(): List<RichModel> {
        val richModels = ArrayList<RichModel>()
        getSpanedList()?.let {
            for (itemSpan in it) {
                richModels.add(itemSpan.model)
            }
        }

        return richModels
    }

    fun removeRichModel(richModel: RichModel) {
        getSpanedList()?.let {
            for (itemSpan in it) {
                if (TextUtils.equals(richModel.getContentRule(), itemSpan.model.getContentRule())) {
                    //向前删除一个字符，@后的内容必须大于一个字符，可以在后面加一个空格
                    text?.let { editable ->
                        val spanStart = editable.getSpanStart(itemSpan)
                        editable.delete(
                            spanStart,
                            editable.getSpanEnd(itemSpan)
                        )
                        post {
                            setSelection(spanStart)
                        }
                    }
                    break
                }
            }
        }
    }

    fun enableKeyListener(enale: Boolean) {
        if (recordKeyListener == null) recordKeyListener = keyListener
        if (enale) keyListener = recordKeyListener
        keyListener = null
    }

    private inner class AtTextSpan(val model: RichModel) : MetricAffectingSpan() {
        override fun updateMeasureState(p: TextPaint) {
        }

        override fun updateDrawState(tp: TextPaint) {
        }
    }
}