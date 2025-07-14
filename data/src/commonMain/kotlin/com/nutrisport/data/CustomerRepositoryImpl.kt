package com.nutrisport.data

import com.nutrisport.data.domain.CustomerRepository
import com.nutrisport.shared.Strings
import com.nutrisport.shared.domain.Customer
import com.nutrisport.shared.util.RequestState
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
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
                val customerCollection = Firebase.firestore.collection(getString(Strings.customer))
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split(" ")?.firstOrNull()
                        ?: getString(Strings.unknown),
                    lastName = user.displayName?.split(" ")?.lastOrNull()
                        ?: getString(Strings.unknown),
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

    override fun readCustomerFlow(): Flow<RequestState<Customer>> = channelFlow {
        try {
            val userId = getCurrentUserId()
            if (userId != null) {
                val database = Firebase.firestore
                database.collection(collectionPath = getString(Strings.customer))
                    .document(userId)
                    .snapshots
                    .collectLatest { document ->
                        if (document.exists) {
                            val customer = Customer(
                                id = document.id,
                                firstName = document.get(field = getString(Strings.firstName)),
                                lastName = document.get(field = getString(Strings.lastName)),
                                email = document.get(field = getString(Strings.email)),
                                city = document.get(field = getString(Strings.city)),
                                postalCode = document.get(field = getString(Strings.postalCode)),
                                address = document.get(field = getString(Strings.address)),
                                phoneNumber = document.get(field = getString(Strings.phoneNumber)),
                                cart = document.get(field = getString(Strings.cart))
                            )
                            send(RequestState.Success(data = customer))
                        } else {
                            send(RequestState.Error(getString(Strings.customerDoesNotExist)))
                        }
                    }
            } else {
                send(RequestState.Error(getString(Strings.errorUserNotAvailable)))
            }
        } catch (e: Exception) {
            send(RequestState.Error(getString(Strings.errorWhileReadingCustomer) + "${e.message}"))
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