package com.nutrisport.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nutrisport.shared.BebasNeueFont
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Strings
import com.nutrisport.shared.Surface
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.component.NotificationCard
import com.nutrisport.shared.component.PrimaryButton
import com.nutrisport.shared.component.ProfileForm
import com.nutrisport.shared.util.DisplayResult
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val screenState = viewModel.screenState

    Scaffold(
        containerColor = Surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Strings.myProfile),
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.LARGE,
                        color = TextPrimary

                    )

                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            painter = painterResource(Resources.Icon.BackArrow),
                            contentDescription = stringResource(Strings.backArrowIcon),
                            tint = IconPrimary
                        )
                    }

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    navigationIconContentColor = IconPrimary,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
                .padding(
                    horizontal = 24.dp,
                )
                .padding(
                    top = 12.dp,
                    bottom = 24.dp
                )
        ) {
            screenState.DisplayResult(
                onLoading = {},
                onSuccess = { state ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        ProfileForm(
                            modifier = Modifier.weight(1f),
                            country = state.country,
                            onCountrySelect = { selectedCountry ->

                            },
                            firstName = state.firstName,
                            onFirstNameChange = {},
                            lastName = state.lastName,
                            onLastNameChange = {},
                            email = state.email,
                            city = state.city,
                            onCityChange = {},
                            postalCode = state.postalCode,
                            onPostalCodeChange = {},
                            address = state.address,
                            onAddressChange = {},
                            phoneNumber = state.phoneNumber?.number,
                            onPhoneNumberChange = {}
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        PrimaryButton(
                            text = stringResource(Strings.update),
                            icon = Resources.Icon.Checkmark,
                            onClick = {}
                        )

                    }
                },
                onError = { message ->
                    NotificationCard(
                        text = message
                    )
                }
            )
        }


    }
}