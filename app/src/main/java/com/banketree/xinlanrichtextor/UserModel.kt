package com.banketree.xinlanrichtextor

import java.io.Serializable


class UserModel : Serializable {

    var name: String? = null

    var id: String? = null

    override fun toString(): String {
        return "$name"
    }
}
