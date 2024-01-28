package ru.cft.shift2023winter.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @field:SerializedName("results")
    val listUsers: ArrayList<User> = arrayListOf(),
    val info: Info
)