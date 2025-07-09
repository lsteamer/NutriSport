package com.nutrisport.profile

import androidx.compose.runtime.Composable
import com.nutrisport.shared.component.ProfileForm


@Composable
fun ProfileScreen() {
    ProfileForm(
        firstName = "John",
        onFirstNameChange = {},
        lastName = "Doe",
        onLastNameChange = {},
        email = "james.c.mcreynolds@example-pet-store.com",
        city = "New York",
        onCityChange = {},
        postalCode = 10001,
        onPostalCodeChange = {},
        address = "123 Main St",
        onAddressChange = {},
        phoneNumber = "",
        onPhoneNumberChange = {}
    )
}