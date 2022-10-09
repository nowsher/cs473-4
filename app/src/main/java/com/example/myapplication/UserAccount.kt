package com.example.myapplication

import java.io.Serializable

data class UserAccount (var id:Int = 0, var firstName:String? = "", var lastName:String? = "", var userEmail:String?, var password:String?)
    : Serializable {

    override fun equals(other: Any?): Boolean {
        var ua:UserAccount  = other as UserAccount
        if (ua.userEmail.isNullOrEmpty() && ua.password.isNullOrEmpty()){
            return false
        }
        if (ua.userEmail.equals(this.userEmail) && ua.password.equals(this.password)){
            return true
        }
        return false
    }
}