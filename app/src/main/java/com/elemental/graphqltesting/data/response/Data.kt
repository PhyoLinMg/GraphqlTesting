package com.elemental.graphqltesting.data.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("insert_todos")
    val insertTodos: InsertTodos
)