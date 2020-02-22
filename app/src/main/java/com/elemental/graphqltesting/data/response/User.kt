package com.elemental.graphqltesting.data.response


import com.google.gson.annotations.SerializedName

data class User(
    val todos: List<Todo>
)