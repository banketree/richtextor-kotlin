package com.ttm.richtextor.model

import java.io.Serializable


class UserModel : Serializable {

    var user_name: String? = null

    var user_id: String? = null

    override fun toString(): String {
        return "$user_name"
    }
}
