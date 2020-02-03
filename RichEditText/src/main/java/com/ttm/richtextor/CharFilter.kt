package com.ttm.richtextor

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.InputFilter

/**
 * 过滤或替换
 * https://www.jianshu.com/p/e2e8dfd92bab
 * */
class CharFilter : InputFilter {

    private val filterChars: CharArray

    constructor(filterCharArray: CharArray?) {
        this.filterChars = filterCharArray ?: CharArray(0)
    }

    /**
     * @param source 输入的文字
     * @param start  输入-0，删除-0
     * @param end    输入-文字的长度，删除-0
     * @param dest   原先显示的内容
     * @param dstart 输入-原光标位置，删除-光标删除结束位置
     * @param dend   输入-原光标位置，删除-光标删除开始位置
     * @return null表示原始输入，""表示不接受输入，其他字符串表示变化值
     */
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {

        if (isNeedReplace(source)) {
            val builder = SpannableStringBuilder()
            builder.append(source)
            builder.append("\n")
            return builder
        }

        return null
    }

    private fun isNeedReplace(source: CharSequence): Boolean {
        val s = source.toString()
        for (filterChar in filterChars) {
            if (s.indexOf(filterChar) >= 0) {
                return true
            }
        }
        return false
    }

    private fun isReplaceChar(c: Char): Boolean {
        for (filterChar in filterChars) {
            if (filterChar == c) {
                return true
            }
        }
        return false
    }

    companion object {
        fun newlineCharFilter(): CharFilter {
            return CharFilter(charArrayOf(',', '.', '!', '，', '。', '！'))
        }
    }
}