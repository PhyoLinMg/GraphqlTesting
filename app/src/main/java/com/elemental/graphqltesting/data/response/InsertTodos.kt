package com.elemental.graphqltesting.data.response


import com.google.gson.annotations.SerializedName

data class InsertTodos(
    @SerializedName("affected_rows")
    val affectedRows: Int,
    val returning: List<Returning>
)