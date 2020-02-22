package com.elemental.graphqltesting.data.response


import com.google.gson.annotations.SerializedName

data class Todo(
    val id: Int,
    @SerializedName("is_public")
    val isPublic: Boolean,
    val title: String
)