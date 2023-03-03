package com.example.hellouser

import android.app.Application
import android.widget.Toast
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
import androidx.navigation.NavHostController
import com.example.hellouser.model.User
import com.example.hellouser.viewmodel.AccountsViewModel

@Composable
fun AuthScreen(navHostController: NavHostController, accountsViewModel: AccountsViewModel) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
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
            try {
                accountsViewModel.add(User(username, password, ""))
                navHostController.navigate("home")
            } catch (exception: Exception) {
                Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Sign In")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAuthScreen() {
    val context = LocalContext.current
    AuthScreen(NavHostController(context), AccountsViewModel(context as Application))
}
