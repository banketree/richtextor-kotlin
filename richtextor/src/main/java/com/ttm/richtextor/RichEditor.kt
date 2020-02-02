package com.ttm.richtextor

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.Html
import android.text.InputFilter
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.TextWatcher
import android.text.style.BackgroundColorSpan
import android.text.style.ImageSpan
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent

import androidx.appcompat.widget.AppCompatEditText

import com.ttm.richtextor.model.RichInsertModel
import com.ttm.richtextor.util.ScreenUtils

import java.util.ArrayList

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
    //最大可输入长度
    var editTextMaxLength = 2000
    private val insertModelList = ArrayList<RichInsertModel>()

    /**
     * 获取普通文本内容
     */
    val richContent: String
        get() {
            var content = text!!.toString()
            if (insertModelList != null && insertModelList.size > 0) {
                for (i in insertModelList.indices) {
                    val inertModel = insertModelList[i]
                    content = content.replace(inertModel.insertContent!!, "")
                }
            }
            return content.trim { it <= ' ' }
        }

    /**
     * 获取特殊字符列表
     */
    val richInsertList: List<RichInsertModel>
        get() {
            val objectsList = ArrayList<RichInsertModel>()
            if (insertModelList != null && insertModelList.size > 0) {
                for (i in insertModelList.indices) {
                    val inertModel = insertModelList[i]
                    objectsList.add(
                        RichInsertModel(
                            inertModel.insertRule,
                            inertModel.insertContent!!.replace(inertModel.insertRule!!, ""),
                            inertModel.insertColor
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

    constructor(context: Context, size: Int) : super(context) {
        initView()
        this.size = size
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        if (isInEditMode) {
            return
        }
        val filters = arrayOf<InputFilter>(InputFilter.LengthFilter(editTextMaxLength))
        setFilters(filters)
        initView()
    }


    /**
     * 初始化控件,一些监听
     */
    private fun initView() {

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

        /**
         * 监听删除键 <br></br>
         * 1.光标在话题后面,将整个话题内容删除 <br></br>
         * 2.光标在普通文字后面,删除一个字符
         *
         */
        this.setOnKeyListener(OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {

                val selectionStart = selectionStart
                val selectionEnd = selectionEnd

                /**
                 * 如果光标起始和结束不在同一位置,删除文本
                 */

                /**
                 * 如果光标起始和结束不在同一位置,删除文本
                 */

                /**
                 * 如果光标起始和结束不在同一位置,删除文本
                 */

                /**
                 * 如果光标起始和结束不在同一位置,删除文本
                 */
                if (selectionStart != selectionEnd) {
                    // 查询文本是否属于目标对象,若是移除列表数据
                    val tagetText = text!!.toString().substring(
                        selectionStart, selectionEnd
                    )
                    for (i in insertModelList.indices) {
                        val `object` = insertModelList[i]
                        if (tagetText == `object`.insertContent) {
                            insertModelList.remove(`object`)
                        }
                    }
                    return@OnKeyListener false
                }


                var lastPos = 0
                val editable = text
                // 遍历判断光标的位置
                for (i in insertModelList.indices) {
                    val objectText = insertModelList[i].insertContent
                    lastPos = text!!.toString().indexOf(objectText!!, lastPos)
                    if (lastPos != -1) {
                        if (selectionStart != 0 && selectionStart >= lastPos && selectionStart <= lastPos + objectText.length) {
                            // 选中话题
                            setSelection(lastPos, lastPos + objectText.length)
                            // 设置背景色
                            editable!!.setSpan(
                                BackgroundColorSpan(BACKGROUND_COLOR),
                                lastPos,
                                lastPos + objectText.length,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            return@OnKeyListener true
                        }
                    }
                    lastPos += objectText.length
                }
            }

            false
        })
    }

    /**
     * 监听光标的位置,若光标处于话题内容中间则移动光标到话题结束位置
     */
    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        if (insertModelList == null || insertModelList.size == 0) {
            return
        }
        var startPostion = 0
        var endPostion = 0
        var insertContent: String? = ""
        for (i in insertModelList.indices) {
            insertContent = insertModelList[i].insertContent
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
            MotionEvent.ACTION_UP -> parent.requestDisallowInterceptTouchEvent(false)
        }
        return super.onTouchEvent(event)
    }

    /**
     * @param insertModel 插入对象
     */
    fun insertSpecialStr(insertModel: RichInsertModel?) {
        if (insertModel == null) {
            return
        }
        //避免插入相同的数据
        for (model in insertModelList) {
            if (model.insertContent.replace(
                    model.insertRule,
                    ""
                ) == insertModel.insertContent && model.insertRule == insertModel.insertRule
            ) {
//                Toast.makeText(mContext, "不可重复插入", Toast.LENGTH_LONG).show()
                return
            }
        }
        val insertRule = insertModel.insertRule
        var insertContent = insertModel.insertContent
        val insertColor = insertModel.insertColor
        if (TextUtils.isEmpty(insertRule) || TextUtils.isEmpty(insertContent)) {
            return
        }
//        if (insertRule == "@") {
//            insertContent = insertRule + insertContent!!
//        } else {
        insertContent = insertRule + insertContent + insertRule
//        }
        insertModel.insertContent = insertContent

        insertModelList.add(insertModel)

        //将特殊字符插入到EditText 中显示
        val index = selectionStart//光标位置
        val editable = text//原先内容
        val spannableStringBuilder = SpannableStringBuilder(editable)
        val htmlText = Html.fromHtml(
            String.format(
                String.format(
                    "<font color='%s'>$insertContent</font>",
                    insertColor!!
                )
            )
        )
        spannableStringBuilder.insert(index, htmlText)
        spannableStringBuilder.insert(index + htmlText.length, "\b")
        text = spannableStringBuilder
        setSelection(index + htmlText.length + 1)
    }

    fun insertIcon(name: String, drawable: Drawable) {
        val curString = text!!.toString()
        if (curString.length + name.length > editTextMaxLength) {
            return
        }

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
        val tagetText = text!!.toString()
        if (TextUtils.isEmpty(tagetText)) {
            insertModelList.clear()
            return
        }
        for (i in insertModelList.indices) {
            val `object` = insertModelList[i]
            if (tagetText.indexOf(`object`.insertContent!!) == -1) {
                insertModelList.remove(`object`)
            }
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
