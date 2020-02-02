package com.ttm.richtextor

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent

import androidx.appcompat.widget.AppCompatEditText

import com.ttm.richtextor.model.RichModel
import com.ttm.richtextor.util.ScreenUtils

import java.util.ArrayList
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import android.view.inputmethod.EditorInfo
import android.text.*
import android.util.Log


/**
 * @author banketree
 * @time 2020/1/6 16:59
 * @description
 * 仿新浪编辑框
 * https://github.com/change9326/RichEditor
 */
@Suppress("DEPRECATION")
class RichEditor : AppCompatEditText {
    private var size: Int = ScreenUtils.dip2px(context, 20f)
    private val insertModelList = ArrayList<RichModel>()

    /**
     * 获取普通文本内容
     */
    val richContent: String
        get() {
            var content = text!!.toString()
            if (insertModelList != null && insertModelList.size > 0) {
                for (i in insertModelList.indices) {
                    val inertModel = insertModelList[i]
                    content = content.replace(inertModel.getContentRule()!!, "")
                }
            }
            return content.trim { it <= ' ' }
        }

    /**
     * 获取特殊字符列表
     */
    val richList: List<RichModel>
        get() {
            val objectsList = ArrayList<RichModel>()
            if (insertModelList != null && insertModelList.size > 0) {
                for (i in insertModelList.indices) {
                    val inertModel = insertModelList[i]
                    objectsList.add(
                        RichModel(
                            inertModel.rule,
                            inertModel.getContentRule()!!.replace(inertModel.getContentRule()!!, ""),
                            inertModel.textColor
                        )
                    )
                }
            }
            return objectsList
        }


    /**
     * 是否可以点击滑动
     */
    var isRequest = false

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    /**
     * 初始化控件,一些监听
     */
    private fun initView() {
//        val filters = arrayOf<InputFilter>(InputFilter.LengthFilter(editTextMaxLength))
//        setFilters(filters)

        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                resolveDeleteSpecialStr()
            }
        })
    }

    private fun deleteChanged(): Boolean {
        Log.w("deleteChanged","selectionStart:$selectionStart  selectionEnd:$selectionEnd")
        /**
         * 如果光标起始和结束不在同一位置,删除文本
         */
        if (selectionStart != selectionEnd) {
            /**
             * 查询文本是否属于目标对象,若是移除列表数据
             * */
            val targetText = text.toString().substring(
                selectionStart, selectionEnd
            )
            var y = 0
            for (i in insertModelList.indices) {
                val insertModel = insertModelList[i - y]
                if (targetText.contains(insertModel.getContentRule())) {
                    insertModelList.remove(insertModel)
                    y++
                }
            }
        }
        var lastPos = 0
        /**
         * 遍历判断光标的位置
         * */
        for (i in insertModelList.indices) {
            val objectText = insertModelList[i].getContentRule()
            lastPos = text.toString().indexOf(objectText, lastPos)
            if (lastPos != -1) {
                if (selectionStart != 0 && selectionStart >= lastPos && selectionStart <= lastPos + objectText.length) {
                    // 选中话题
                    setSelection(lastPos, lastPos + objectText.length)
                }
            }
            lastPos += objectText.length
        }

        return false
    }

    /**
     * 监听光标的位置,若光标处于话题内容中间则移动光标到话题结束位置
     */
    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        if (insertModelList == null || insertModelList.isEmpty()) {
            return
        }
        var startPostion = 0
        var endPostion = 0
        var insertContent: String? = ""
        for (i in insertModelList.indices) {
            insertContent = insertModelList[i].getContentRule()
            startPostion = text!!.toString().indexOf(insertContent!!)
            endPostion = startPostion + insertContent.length
            if (startPostion != -1 && selStart > startPostion
                && selStart <= endPostion
            ) {// 若光标处于话题内容中间则移动光标到话题结束位置
                setSelection(endPostion)
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(isRequest)
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP ->
                parent.requestDisallowInterceptTouchEvent(false)
        }
        return super.onTouchEvent(event)
    }


    /**
     * @param model 插入对象
     */
    fun insertSpecialStr(model: RichModel) {
        //避免插入相同的数据
        for (model in insertModelList) {
            if (model.getContentRule().replace(
                    model.rule,
                    ""
                ) == model.getContentRule() && model.rule == model.rule
            ) {
//                Toast.makeText(mContext, "不可重复插入", Toast.LENGTH_LONG).show()
                return
            }
        }
        val insertRule = model.rule
        var insertContent = model.getContentRule()
        val insertColor = model.textColor
        if (TextUtils.isEmpty(insertRule) || TextUtils.isEmpty(insertContent)) {
            return
        }
        insertModelList.add(model)

        //将特殊字符插入到EditText 中显示
        val index = selectionStart//光标位置
        val editable = text//原先内容
        val spannableStringBuilder = SpannableStringBuilder(editable)
        val htmlText = model.getContentHtml()

        spannableStringBuilder.insert(index, htmlText)
        spannableStringBuilder.insert(index + htmlText.length, "\b")

        text = spannableStringBuilder
        setSelection(index + htmlText.length + 1)
    }

    fun insertIcon(name: String, drawable: Drawable) {
        val curString = text!!.toString()
//        if (curString.length + name.length > editTextMaxLength) {
//            return
//        }

        //这里设置图片的大小
        drawable.setBounds(0, 0, size, size)
        val imageSpan = ImageSpan(drawable)
        val spannableString = SpannableString(name)
        spannableString.setSpan(
            imageSpan,
            0,
            spannableString.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val index = Math.max(selectionStart, 0)
        val spannableStringBuilder = SpannableStringBuilder(text)
        spannableStringBuilder.insert(index, spannableString)

        text = spannableStringBuilder
        setSelection(index + spannableString.length)
    }

    /**
     * 删除缓存列表
     */
    private fun resolveDeleteSpecialStr() {
        val targetText = text.toString()
        if (TextUtils.isEmpty(targetText)) {
            insertModelList.clear()
            return
        }

        for (i in insertModelList.indices) {
            val insertModel = insertModelList[i]
            if (targetText.indexOf(insertModel.getContentRule()) == -1) {
                insertModelList.remove(insertModel)
            }
        }
    }

    /**
     * 解决部分机型不支持监听delete键
     */
    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        return XInputConnection(
            super.onCreateInputConnection(outAttrs),
            true
        )
    }

    private inner class XInputConnection(target: InputConnection, mutable: Boolean) :
        InputConnectionWrapper(target, mutable) {

        override fun sendKeyEvent(event: KeyEvent): Boolean {
            if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                deleteChanged()
                return true
            }
            return super.sendKeyEvent(event)
        }

        override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
            return if (beforeLength == 1 && afterLength == 0) {
                sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DEL
                    )
                ) && sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DEL
                    )
                )
            } else super.deleteSurroundingText(beforeLength, afterLength)
        }
    }

    companion object {
        private val TAG = "RichEditor"
        /**
         * 默认,话题背景高亮颜色
         */
        private val BACKGROUND_COLOR = Color.parseColor("#FFDEAD")
    }
}
