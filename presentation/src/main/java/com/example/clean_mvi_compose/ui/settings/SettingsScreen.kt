package com.example.clean_mvi_compose.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Card
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clean_mvi_compose.R

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
                title = { Text(stringResource(R.string.settings)) },
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
                Text(stringResource(R.string.nameAndSurname), fontWeight = FontWeight.SemiBold)
                Text(
                    text = "ivan@email.com",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    stringResource(R.string.enterOrRegister),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    stringResource(R.string.SyncAndBackups),
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
                title = stringResource(R.string.theme),
                value = if (darkTheme) stringResource(R.string.darkTheme) else stringResource(R.string.lightTheme),
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
                title = stringResource(R.string.language),
                value = language
            )

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            PreferenceItem(
                icon = Icons.Default.AttachMoney,
                title = stringResource(R.string.mainСurrency),
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
                title = stringResource(R.string.aboutApp),
                value = stringResource(R.string.currentVersion)
            )

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            PreferenceItem(
                icon = Icons.AutoMirrored.Filled.Help,
                title = stringResource(R.string.help),
                value = stringResource(R.string.faqAndSupport)
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
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        onBack = {}
    )
}
