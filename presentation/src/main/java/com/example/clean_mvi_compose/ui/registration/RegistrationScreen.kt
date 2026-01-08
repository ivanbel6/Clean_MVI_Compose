package com.example.clean_mvi_compose.ui.registration

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

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
                            contentDescription = "Назад"
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
                    text = "Создание аккаунта",
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "Введите данные для регистрации",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { onIntent(RegistrationIntent.EmailChanged(it)) },
                    label = { Text("Email") },
                    isError = state.emailError != null,
                    supportingText = {
                        state.emailError?.let { Text(it) }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = { onIntent(RegistrationIntent.PasswordChanged(it)) },
                    label = { Text("Пароль") },
                    isError = state.passwordError != null,
                    supportingText = {
                        state.passwordError?.let { Text(it) }
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
                    Text("Зарегистрироваться")
                }

                TextButton(
                    onClick = { onIntent(RegistrationIntent.NavigateToLogin) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Уже есть аккаунт? Войти")
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {

                Text(
                    text = "Или продолжить через",
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
                            Text("G", style = MaterialTheme.typography.titleMedium)
                        }
                    )

                    SocialIconButton(
                        onClick = { onIntent(RegistrationIntent.GitHubSignIn) },
                        icon = {
                            Text("GH", style = MaterialTheme.typography.titleSmall)
                        }
                    )

                    SocialIconButton(
                        onClick = { onIntent(RegistrationIntent.AppleSignIn) },
                        icon = {
                            Text("", style = MaterialTheme.typography.titleMedium)
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


