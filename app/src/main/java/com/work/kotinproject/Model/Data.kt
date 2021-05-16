package com.work.kotinproject.Model

import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("page")
    val page: Int=0
    @SerializedName("pre_page")
    val prePage:Int=0
    @SerializedName("total")
    val total:Int=0
    @SerializedName("data")
    val users: List<User> = TODO()

}