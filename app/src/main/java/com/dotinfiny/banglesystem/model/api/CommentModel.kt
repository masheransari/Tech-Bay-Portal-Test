package com.dotinfiny.banglesystem.model.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class CommentModel {
    @SerializedName("postId")
    var postId: Int = 0

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("email")
    var email: String? = ""

    @SerializedName("body")
    var body: String? = ""
}