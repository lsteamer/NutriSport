package com.nutrisport.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.nutrisport.auth.component.GoogleButton
import com.nutrisport.shared.Alpha
import com.nutrisport.shared.BebasNeueFont
import com.nutrisport.shared.Strings
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.Surface
import com.nutrisport.shared.SurfaceBrand
import com.nutrisport.shared.SurfaceError
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.TextSecondary
import com.nutrisport.shared.TextWhite
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@Composable
fun AuthScreen(
    navigateToHome: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val viewModel = koinViewModel<AuthViewModel>()
    val messageBarState = rememberMessageBarState()
    var loadingState by remember { mutableStateOf(false) }
    val errorUnknownString: String = stringResource(Strings.errorUnknown)
    val errorNetworkString: String = stringResource(Strings.errorNetwork)
    val errorInternetString: String = stringResource(Strings.errorInternet)
    val errorIdTokenString: String = stringResource(Strings.errorIdToken)
    val errorSignInCancelledString: String = stringResource(Strings.errorSignInCancelled)


    Scaffold { padding ->
        ContentWithMessageBar(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
            contentBackgroundColor = Surface,
            successContainerColor = SurfaceBrand,
            successContentColor = TextPrimary,
            errorContainerColor = SurfaceError,
            errorContentColor = TextWhite
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Strings.appName),
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(Alpha.HALF),
                        text = stringResource(Strings.signInToContinue),
                        textAlign = TextAlign.Center,
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary
                    )

                }
                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { result ->
                        result.onSuccess { user ->
                            viewModel.createCustomer(
                                user = user,
                                onSuccess = {
                                    scope.launch {
                                        messageBarState.addSuccess(getString(Strings.authSuccess))
                                        delay(2000)
                                        navigateToHome()
                                    }
                                },
                                onError = { message -> messageBarState.addError(message) }
                            )
                            loadingState = false
                        }.onFailure { error ->
                            if (error.message?.contains(errorNetworkString) == true) {
                                messageBarState.addError(errorInternetString)
                            } else if (error.message?.contains(errorIdTokenString) == true) {
                                messageBarState.addError(errorSignInCancelledString)
                            } else {
                                messageBarState.addError(error.message ?: errorUnknownString)
                            }
                            loadingState = false
                        }
                    }
                ) {
                    GoogleButton(
                        loading = loadingState,
                        onClick = {
                            loadingState = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )
                }
            }
        }
    }
}