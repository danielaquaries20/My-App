package com.daniel.myapp.app_tour.api.response

import com.crocodic.core.api.ModelResponse
import com.daniel.myapp.app_tour.model.DataProduct
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    val products: List<DataProduct>
) : ModelResponse()
