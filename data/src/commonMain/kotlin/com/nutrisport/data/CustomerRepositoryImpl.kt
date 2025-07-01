package com.nutrisport.data

import com.nutrisport.data.domain.CustomerRepository
import com.nutrisport.shared.EnglishStrings
import com.nutrisport.shared.domain.Custome
import dev.gitlive.firebase.Firebase

import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class CustomerRepositoryImpl : CustomerRepository {
    override fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if (user != null) {
                val customerCollection = Firebase.firestore.collection(EnglishStrings.customers)
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split(" ")?.firstOrNull() ?: EnglishStrings.unknown,
                    lastName = user.displayName?.split(" ")?.lastOrNull() ?: EnglishStrings.unknown,
                    email = user?.email ?: EnglishStrings.unknown,
                )

                val customerExists = customerCollection.document(user.uid).get().exists

                if(customerExists){
                    onSuccess()
                } else {
                    customerCollection.document(user.uid).set(customer)
                    onSuccess()
                }
            } else {
                onError(EnglishStrings.errorUserNotAvailable)
            }

        } catch (e: Exception) {

            onError(EnglishStrings.errorUserCreation+e.message)
        }
    }
}