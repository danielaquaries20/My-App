package com.daniel.myapp.app_tour.api.response

import com.daniel.myapp.app_tour.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: User?
)