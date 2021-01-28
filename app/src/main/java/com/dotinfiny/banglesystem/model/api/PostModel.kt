package com.dotinfiny.banglesystem.model.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class PostModel {

    @SerializedName("userId")
    var userId: Int = 0

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String? =""

    @SerializedName("body")
    var body: String? =""

}