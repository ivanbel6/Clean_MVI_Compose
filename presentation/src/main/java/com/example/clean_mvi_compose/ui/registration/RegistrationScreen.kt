package com.example.clean_mvi_compose.ui.registration

import com.example.clean_mvi_compose.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.domainErrors.AccountError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onIntent: (RegistrationIntent) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.hello)
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.createAcc),
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = stringResource(R.string.enterRegistrationData),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { onIntent(RegistrationIntent.EmailChanged(it)) },
                    label = { Text("Email") },
                    isError = state.regEmailError != null,
                    supportingText = {
                        emailErrorText(state.regEmailError)?.let {
                            Text(text = it)
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )


                OutlinedTextField(
                    value = state.password,
                    onValueChange = { onIntent(RegistrationIntent.PasswordChanged(it)) },
                    label = { Text(stringResource(R.string.password)) },
                    isError = state.regPasswordError != null,
                    supportingText = {
                        passwordErrorText(state.regPasswordError)?.let {
                            Text(text = it)
                        }
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { onIntent(RegistrationIntent.Submit) },
                    enabled = state.isSubmitEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(stringResource(R.string.register))
                }

                TextButton(
                    onClick = { onIntent(RegistrationIntent.NavigateToLogin) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(stringResource(R.string.alreadyHaveAccThenEnter))
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {

                Text(
                    text = stringResource(R.string.orEnterThrough),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    SocialIconButton(
                        onClick = { onIntent(RegistrationIntent.GoogleSignIn) },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_google),
                                contentDescription = "Google sign in",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    )

                }
            }
        }
    }
}


@Composable
private fun SocialIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        tonalElevation = 2.dp,
        modifier = Modifier.size(56.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        state = RegistrationState(),
        onIntent = {},
        onBackClick = {}
    )
}
@Composable
fun emailErrorText(error: AccountError.Register?): String? =
    when (error) {
        AccountError.Register.EMAIL_ERROR ->
            stringResource(R.string.error_invalid_email)
        else -> null
    }

@Composable
fun passwordErrorText(error: AccountError.Register?): String? =
    when (error) {
        AccountError.Register.PASSWORD_ERROR ->
            stringResource(R.string.error_invalid_password)
        else -> null
    }


