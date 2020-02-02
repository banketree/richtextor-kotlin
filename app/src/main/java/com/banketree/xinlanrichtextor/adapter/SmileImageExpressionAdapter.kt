package com.banketree.xinlanrichtextor.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat

import com.ttm.richtextor.R
import com.ttm.richtextor.RichEditor


class SmileImageExpressionAdapter(
    context: Context,
    textViewResourceId: Int,
    objects: List<String>
) : ArrayAdapter<String>(context, textViewResourceId, objects) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.smile_image_row_expression, null)
        }

        val imageView = convertView!!.findViewById(R.id.iv_expression) as ImageView

        val filename = getItem(position)
        filename?.let {
            val resId = ParseIconResId(it, context)
            imageView.setImageResource(resId)
        }
        return convertView
    }


    companion object {

        fun ParseIconResId(name: String, context: Context): Int {
            var name = name
            name = name.substring(1, name.length - 1)
            return context.resources.getIdentifier(name, "drawable", context.packageName)
        }

        fun ParseIconResId2(name: String, context: Context): Drawable? {
            return ContextCompat.getDrawable(context, ParseIconResId(name, context))
        }
    }
}