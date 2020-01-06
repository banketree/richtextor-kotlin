package com.ttm.richtextor.model


class InsertModel(var insertRule: String?, var insertContent: String?, var insertColor: String?) {

    override fun toString(): String {
        return "InsertModel{" +
                "insertRule='" + insertRule + '\''.toString() +
                ", insertContent='" + insertContent + '\''.toString() +
                ", insertColor='" + insertColor + '\''.toString() +
                '}'.toString()
    }
}