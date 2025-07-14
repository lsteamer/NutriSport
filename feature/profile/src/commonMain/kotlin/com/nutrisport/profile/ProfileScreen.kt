package com.nutrisport.profile

import ContentWithMessageBar
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
import com.nutrisport.shared.component.InfoCard
import com.nutrisport.shared.component.LoadingCard
import com.nutrisport.shared.component.PrimaryButton
import com.nutrisport.shared.component.ProfileForm
import com.nutrisport.shared.util.DisplayResult
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val screenReady = viewModel.screenReady
    val screenState = viewModel.screenState
    val isFormValid = viewModel.isFormValid
    val messageBarState = rememberMessageBarState()

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
        ContentWithMessageBar(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            contentBackgroundColor = Surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(
                        horizontal = 24.dp,
                    )
                    .padding(
                        top = 12.dp,
                        bottom = 24.dp
                    )
            ) {
                screenReady.DisplayResult(
                    onLoading = { LoadingCard(Modifier.fillMaxSize()) },
                    onSuccess = {
                        Column(modifier = Modifier.fillMaxSize()) {
                            ProfileForm(
                                modifier = Modifier.weight(1f),
                                country = screenState.country,
                                onCountrySelect = viewModel::updateCountry,
                                firstName = screenState.firstName,
                                onFirstNameChange = viewModel::changeFirstName,
                                lastName = screenState.lastName,
                                onLastNameChange = viewModel::changeLastName,
                                email = screenState.email,
                                city = screenState.city,
                                onCityChange = viewModel::changeCity,
                                postalCode = screenState.postalCode,
                                onPostalCodeChange = viewModel::changePostalCode,
                                address = screenState.address,
                                onAddressChange = viewModel::changeAddress,
                                phoneNumber = screenState.phoneNumber?.number,
                                onPhoneNumberChange = viewModel::changePhoneNumber
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            val successfulUpdate = stringResource(Strings.successfulUpdate)
                            PrimaryButton(
                                text = stringResource(Strings.update),
                                icon = Resources.Icon.Checkmark,
                                enabled = isFormValid,
                                onClick = {
                                    viewModel.updateCustomer(
                                        onSuccess = {
                                            messageBarState.addSuccess(successfulUpdate)
                                        },
                                        onError = { message ->
                                            messageBarState.addError(message)
                                        }
                                    )
                                }
                            )

                        }
                    },
                    onError = { message ->
                        InfoCard(
                            image = Resources.Image.Cat,
                            title = stringResource(Strings.error),
                            subtitle = message,
                        )
                    }
                )
            }
        }
    }
}