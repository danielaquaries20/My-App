package com.daniel.myapp.app_tour.api.response

import com.crocodic.core.api.ModelResponse
import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data")
    val data: AuthResponse?
) : ModelResponse()
