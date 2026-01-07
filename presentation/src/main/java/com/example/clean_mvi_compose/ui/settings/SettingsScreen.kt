package com.example.clean_mvi_compose.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {

    val isAuthorized = false
    val darkTheme = remember { mutableStateOf(false) }
    val language = remember { mutableStateOf("Русский") }
    val currency = remember { mutableStateOf("USD") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Настройки") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                AccountCard(isAuthorized)
            }

            item {
                PreferencesCard(
                    darkTheme = darkTheme.value,
                    onThemeToggle = { darkTheme.value = it },
                    language = language.value,
                    currency = currency.value
                )
            }

            item {
                SystemCard()
            }
        }
    }
}

@Composable
private fun AccountCard(isAuthorized: Boolean) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .clickable { }
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Spacer(Modifier.height(12.dp))

            if (isAuthorized) {
                Text("Иван Иванов", fontWeight = FontWeight.SemiBold)
                Text(
                    text = "ivan@email.com",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    "Войти или зарегистрироваться",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Синхронизация и резервные копии",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun PreferencesCard(
    darkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    language: String,
    currency: String
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            PreferenceItem(
                icon = Icons.Default.DarkMode,
                title = "Тема",
                value = if (darkTheme) "Тёмная" else "Светлая",
                trailing = {
                    Switch(
                        checked = darkTheme,
                        onCheckedChange = onThemeToggle
                    )
                }
            )

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            PreferenceItem(
                icon = Icons.Default.Language,
                title = "Язык",
                value = language
            )

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            PreferenceItem(
                icon = Icons.Default.AttachMoney,
                title = "Основная валюта",
                value = currency
            )
        }
    }
}

@Composable
private fun SystemCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            PreferenceItem(
                icon = Icons.Default.Info,
                title = "О приложении",
                value = "Версия 1.0.0"
            )

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            PreferenceItem(
                icon = Icons.AutoMirrored.Filled.Help,
                title = "Помощь",
                value = "FAQ и поддержка"
            )
        }
    }
}

@Composable
private fun PreferenceItem(
    icon: ImageVector,
    title: String,
    value: String,
    trailing: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(icon, null)
            Spacer(Modifier.width(16.dp))
            Column {
                Text(title)
                Text(
                    value,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        trailing?.invoke()
    }
}
