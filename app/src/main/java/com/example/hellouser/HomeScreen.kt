package com.example.hellouser

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.hellouser.viewmodel.AccountsViewModel

@Composable
fun UsersScreen(accountsViewModel: AccountsViewModel) {
    val userList = accountsViewModel.userList

    LazyColumn(modifier = Modifier
        .padding(vertical = 15.dp)) {
        items(userList) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(it.username, style = MaterialTheme.typography.h5)
                Spacer(Modifier.weight(1f))
                Button(onClick = {
                    accountsViewModel.remove(it)
                }) {
                    Text("Sign Out")
                }
            }
            Divider()
        }
    }
}

@Composable
fun HomeScreen(navHostController: NavHostController, accountsViewModel: AccountsViewModel) {
    ComposableLifecycle { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            accountsViewModel.load()
        }
    }

    Scaffold(
        topBar = {},
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)
            ) {
                Text("User list", style = MaterialTheme.typography.h2)
                Divider()
                UsersScreen(accountsViewModel)
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
