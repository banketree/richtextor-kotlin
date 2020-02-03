package com.banketree.xinlanrichtextor.core


import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.viewpager.widget.ViewPager
import com.banketree.xinlanrichtextor.R
import com.banketree.xinlanrichtextor.RichEditor

import com.banketree.xinlanrichtextor.adapter.ExpressionPagerAdapter
import com.banketree.xinlanrichtextor.adapter.SmileImageExpressionAdapter
import com.banketree.xinlanrichtextor.util.ScreenUtils

import java.util.ArrayList


/**
 * Created by MryU93 on 2017/6/15.
 * Desc:
 */

class EmojiLayout : LinearLayout {
    internal var edittextBarVPager: ViewPager? = null
    internal var edittextBarViewGroupFace: LinearLayout? = null
    internal var edittextBarLlFaceContainer: LinearLayout? = null
    internal var edittextBarMore: LinearLayout? = null

    var editTextEmoji: RichEditor? = null
    private val reslist = ArrayList<String>()
    private lateinit var imageFaceViews: Array<ImageView?>


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }


    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_emoji_container, this, true)
        if (isInEditMode) {
            return
        }
        initViews(view)
    }


    /**
     * 初始化View
     */
    private fun initViews(view: View?) {
        view?.apply {
            edittextBarVPager = findViewById(R.id.edittext_bar_vPager)
            edittextBarViewGroupFace = findViewById(R.id.edittext_bar_viewGroup_face)
            edittextBarLlFaceContainer = findViewById(R.id.edittext_bar_ll_face_container)
            edittextBarMore = findViewById(R.id.edittext_bar_more)
        }

        val size = ScreenUtils.dip2px(context, 5f)
        val marginSize = ScreenUtils.dip2px(context, 5f)
        // 表情list
        val smile = ArrayList<String>()
        for (i in 1..IMG_LIST.size) {
            smile.add("[e$i]")
        }
        reslist.addAll(smile)
        // 初始化表情viewpager
        val views = ArrayList<View>()
        val gridChildSize = Math.ceil(reslist.size.toDouble() / 21.toDouble()).toInt()
        for (i in 1..gridChildSize) {
            views.add(getGridChildView(i))
        }
        var imageViewFace: ImageView
        imageFaceViews = arrayOfNulls(views.size)
        for (i in views.indices) {
            val margin = LinearLayout.LayoutParams(size, size)
            margin.setMargins(marginSize, 0, 0, 0)
            imageViewFace = ImageView(context)
            imageViewFace.layoutParams = ViewGroup.LayoutParams(size, size)
            imageFaceViews[i] = imageViewFace
            if (i == 0) {
                imageFaceViews[i]?.setBackgroundResource(R.drawable.page_indicator_focused)
            } else {
                imageFaceViews[i]?.setBackgroundResource(R.drawable.page_indicator_unfocused)
            }
            edittextBarViewGroupFace?.addView(imageFaceViews[i], margin)
        }
        edittextBarVPager?.adapter = ExpressionPagerAdapter(views)
        edittextBarVPager?.addOnPageChangeListener(GuidePageChangeListener())

    }

    /**
     * 获取表情的gridview的子view
     */
    private fun getGridChildView(i: Int): View {
        val view = View.inflate(context, R.layout.expression_gridview, null)
        val gv = view.findViewById(R.id.gridview) as LockGridView
        val list = ArrayList<String>()
        val startInd = (i - 1) * 21
        if (startInd + 21 >= reslist.size) {
            list.addAll(reslist.subList(startInd, startInd + (reslist.size - startInd)))
        } else {
            list.addAll(reslist.subList(startInd, startInd + 21))
        }
        val smileImageExpressionAdapter = SmileImageExpressionAdapter(context, 1, list)
        gv.adapter = smileImageExpressionAdapter
        gv.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val filename = smileImageExpressionAdapter.getItem(position)
                filename?.let {
                    SmileImageExpressionAdapter.ParseIconResId2(it, context)?.let { drawable ->
                        editTextEmoji!!.insertIcon(
                            it,
                            drawable
                        )
                    }
                }
            }
        return view
    }

    internal inner class GuidePageChangeListener : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(arg0: Int) {}

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageSelected(arg0: Int) {

            for (i in imageFaceViews.indices) {
                imageFaceViews[arg0]?.setBackgroundResource(R.drawable.page_indicator_focused)

                if (arg0 != i) {
                    imageFaceViews[i]?.setBackgroundResource(R.drawable.page_indicator_unfocused)
                }
            }
        }
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard() {
        val context = context as Activity
        if (context.window.attributes.softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (context.currentFocus != null) {
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    context.currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }

    /**
     * 显示键盘
     */
    fun showKeyboard() {
        editTextEmoji!!.requestFocus()
        val inputManager =
            editTextEmoji!!.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(editTextEmoji, 0)
    }

    fun setEditTextSmile(editTextSmile: RichEditor) {
        this.editTextEmoji = editTextSmile
        editTextSmile.setOnClickListener { visibility = View.GONE }
    }

    companion object {
        private val IMG_LIST = intArrayOf(
            R.drawable.e1,
            R.drawable.e2,
            R.drawable.e3,
            R.drawable.e4,
            R.drawable.e5,
            R.drawable.e6,
            R.drawable.e7,
            R.drawable.e8,
            R.drawable.e9,
            R.drawable.e10,
            R.drawable.e11,
            R.drawable.e12,
            R.drawable.e13,
            R.drawable.e14,
            R.drawable.e15,
            R.drawable.e16,
            R.drawable.e17,
            R.drawable.e18,
            R.drawable.e19,
            R.drawable.e20,
            R.drawable.e21,
            R.drawable.e22,
            R.drawable.e23,
            R.drawable.e24,
            R.drawable.e25,
            R.drawable.e26,
            R.drawable.e27,
            R.drawable.e28,
            R.drawable.e29,
            R.drawable.e30,
            R.drawable.e31,
            R.drawable.e32,
            R.drawable.e33,
            R.drawable.e34,
            R.drawable.e35,
            R.drawable.e36,
            R.drawable.e37
        )
    }
}