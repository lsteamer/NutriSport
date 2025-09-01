package com.nutrisport.data

import com.nutrisport.data.domain.AdminRepository
import com.nutrisport.shared.Strings
import com.nutrisport.shared.domain.Product
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.jetbrains.compose.resources.getString

class AdminRepositoryImpl : AdminRepository {
    override fun getCurrentUserID() = Firebase.auth.currentUser?.uid

    override suspend fun createNewProduct(
        product: Product,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val currentUserId = getCurrentUserID()
            if (currentUserId != null) {
                val firestore = Firebase.firestore
                val productCollection =
                    firestore.collection(collectionPath = "product")
                productCollection.document(product.id).set(product.title.lowercase())
                onSuccess()
            } else {
                onError(getString(Strings.errorUserNotAvailable))
            }
        } catch (e: Exception) {
            onError(getString(Strings.errorWhileCreatingProduct, e.message ?: ""))
        }
    }
}