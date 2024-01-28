package ru.cft.shift2023winter.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    @field:SerializedName("dob")
    val dateOfBirth: DateOfBirth,
    val registered: Registered,
    val phone: String,
    val cell: String,
    val id: Id,
    val picture: Picture,
    val nat: String
)