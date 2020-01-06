package com.ttm.richtextor.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView

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
            val resId = RichEditor.ParseIconResId(it)
            imageView.setImageResource(resId)
        }
        return convertView
    }

}