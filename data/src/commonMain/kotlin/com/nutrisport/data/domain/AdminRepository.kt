package com.nutrisport.data.domain

import com.nutrisport.shared.domain.Product

interface AdminRepository {
    fun getCurrentUserID(): String?
    suspend fun createNewProduct(
        product: Product,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
}