package com.example.hellouser

import android.accounts.AccountManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val accountManager = AccountManager.get(context)

    val accountList = accountManager.accounts.map { "${it.name} - ${it.type}" }
    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 15.dp)) {
        items(accountList) {
            Box(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(it, style = MaterialTheme.typography.h5)
            }
        }
    }
}
