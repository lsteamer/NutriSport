package com.nutrisport.data

import com.nutrisport.data.domain.CustomerRepository
import com.nutrisport.shared.Strings
import com.nutrisport.shared.domain.Customer
import com.nutrisport.shared.util.RequestState
import dev.gitlive.firebase.Firebase

import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.jetbrains.compose.resources.getString

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
                val customerCollection = Firebase.firestore.collection(getString(Strings.customers))
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split(" ")?.firstOrNull() ?: getString(Strings.unknown),
                    lastName = user.displayName?.split(" ")?.lastOrNull() ?: getString(Strings.unknown),
                    email = user?.email ?: getString(Strings.unknown),
                )

                val customerExists = customerCollection.document(user.uid).get().exists

                if (customerExists) {
                    onSuccess()
                } else {
                    customerCollection.document(user.uid).set(customer)
                    onSuccess()
                }
            } else {
                onError(getString(Strings.errorUserNotAvailable))
            }

        } catch (e: Exception) {

            onError(getString(Strings.errorUserCreation) + e.message)
        }
    }

    override suspend fun signOut(): RequestState<Unit> {
        return try {
            Firebase.auth.signOut()
            RequestState.Success(Unit)
            RequestState.Success(data = Unit)
        } catch (e: Exception) {
            RequestState.Error(getString(Strings.errorWhileSigningOut) + e.message)
        }
    }
}