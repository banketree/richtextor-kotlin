package com.ttm.richtextor.model


class RichInsertModel(var insertRule: String, var insertContent: String, var insertColor: String) {

    override fun toString(): String {
        return "RichInsertModel{" +
                "insertRule='" + insertRule + '\''.toString() +
                ", insertContent='" + insertContent + '\''.toString() +
                ", insertColor='" + insertColor + '\''.toString() +
                '}'.toString()
    }
}