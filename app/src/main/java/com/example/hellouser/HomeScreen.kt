package com.example.hellouser

import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun UsersScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val accountManager = AccountManager.get(context)
    val callback = AccountManagerCallback<Bundle> {
        when {
            it.isDone -> {
                Toast.makeText(context, "User signed out!", Toast.LENGTH_SHORT).show()
            }
            it.isCancelled -> {
                Toast.makeText(context, "Sign out cancelled!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val accountList = accountManager.accounts
    LazyColumn(modifier = Modifier
        .padding(vertical = 15.dp)) {
        items(accountList) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(it.name, style = MaterialTheme.typography.h5)
                Spacer(Modifier.weight(1f))
                Button(onClick = {
                    accountManager.removeAccount(it, null, callback, null)
                }) {
                    Text("Sign Out")
                }
            }
            Divider()
        }
    }
}

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {},
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)
            ) {
                Text("User list", style = MaterialTheme.typography.h2)
                Divider()
                UsersScreen(navHostController = navHostController)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate("auth")
                },
                backgroundColor = MaterialTheme.colors.secondary,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.padding(20.dp)
    )
}
