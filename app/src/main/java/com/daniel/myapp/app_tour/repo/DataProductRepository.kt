package com.daniel.myapp.app_tour.repo

import com.daniel.myapp.app_tour.model.DataProduct
import kotlinx.coroutines.flow.Flow

interface DataProductRepository {

    fun getProducts(keyword: String): Flow<List<DataProduct>>

}