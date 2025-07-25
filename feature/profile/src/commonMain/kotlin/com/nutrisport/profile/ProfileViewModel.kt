package com.nutrisport.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrisport.data.domain.CustomerRepository
import com.nutrisport.shared.domain.Country
import com.nutrisport.shared.domain.Customer
import com.nutrisport.shared.domain.PhoneNumber
import com.nutrisport.shared.util.RequestState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class ProfileScreenState(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val city: String? = null,
    val postalCode: Int? = null,
    val address: String? = null,
    val country: Country = Country.Mexico,
    val phoneNumber: PhoneNumber? = null
)

class ProfileViewModel(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    var screenReady: RequestState<Unit> by mutableStateOf(RequestState.Loading)
    var screenState: ProfileScreenState by mutableStateOf(ProfileScreenState())
        private set

    val isFormValid: Boolean
        get() = with(screenState) {
            firstName.length in 3..50 &&
                    lastName.length in 3..50 &&
                    city?.length in 3..50 &&
                    postalCode != null && postalCode.toString().length in 3..7 &&
                    address?.length in 3..50 &&
                    phoneNumber?.number?.length in 3..17
        }

    init {
        viewModelScope.launch {
            customerRepository.readCustomerFlow().collectLatest { data ->
                if (data.isSuccess()) {
                    val fetchedCustomer = data.getSuccessData()
                    screenState = ProfileScreenState(
                        id = fetchedCustomer.id,
                        firstName = fetchedCustomer.firstName,
                        lastName = fetchedCustomer.lastName,
                        email = fetchedCustomer.email,
                        city = fetchedCustomer.city,
                        postalCode = fetchedCustomer.postalCode,
                        address = fetchedCustomer.address,
                        phoneNumber = fetchedCustomer.phoneNumber,
                        country = Country.entries.firstOrNull { it.dialCode == fetchedCustomer.phoneNumber?.dialCode }
                            ?: Country.Mexico

                    )
                    screenReady = RequestState.Success(Unit)
                } else if (data.isError()) {
                    screenReady = RequestState.Error(data.getErrorMessage())
                }
            }
        }
    }

    fun updateCustomer(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            customerRepository.updateCustomer(
                customer = screenState.toCustomer(),
                onSuccess = onSuccess,
                onError = onError
            )
        }
    }

    fun changeFirstName(value: String) {
        screenState = screenState.copy(firstName = value)
    }

    fun changeLastName(value: String) {
        screenState = screenState.copy(lastName = value)
    }

    fun changeCity(value: String) {
        screenState = screenState.copy(city = value)
    }

    fun changePostalCode(value: Int?) {
        screenState = screenState.copy(postalCode = value)
    }

    fun changeAddress(value: String) {
        screenState = screenState.copy(address = value)
    }

    fun updateCountry(value: Country) {
        screenState = screenState.copy(
            country = value,
            phoneNumber = screenState.phoneNumber?.copy(
                dialCode = value.dialCode
            )
        )
    }

    fun changePhoneNumber(value: String) {
        screenState =
            screenState
                .copy(
                    phoneNumber = PhoneNumber(
                        dialCode = screenState.country.dialCode,
                        number = value
                    )
                )
    }

    private fun ProfileScreenState.toCustomer(): Customer {
        return Customer(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            city = city,
            postalCode = postalCode,
            address = address,
            phoneNumber = phoneNumber
        )
    }
}