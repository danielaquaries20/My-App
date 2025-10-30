package com.daniel.myapp.app_tour.model

import com.crocodic.core.api.ModelResponse
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nama_lengkap")
    var fullName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("nomor_hp")
    var phone: Int
) : ModelResponse()