package com.example.hellouser

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen() {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = { Text("Username") },
            placeholder = { Text("Enter username") }
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Password") },
            placeholder = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(onClick = {
            addAccount(context, username, password, "MY_TOKEN")
        }) {
            Text("Sign In")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAuthScreen() {
    AuthScreen()
}

fun addAccount(context: Context, username: String, password: String, authToken: String) {
    val account = Account(username, context.getString(R.string.account_type))
    val accountManager = AccountManager.get(context)
    accountManager.addAccountExplicitly(account, password, null)
    accountManager.setAuthToken(account, "TOKEN_TYPE", authToken)
}
