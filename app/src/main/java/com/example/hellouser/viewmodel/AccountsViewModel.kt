package com.example.hellouser.viewmodel

import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellouser.model.User
import kotlinx.coroutines.launch

class AccountsViewModel(application: Application): AndroidViewModel(application) {
    private val accountManager = AccountManager.get(application)
    var userList by mutableStateOf(emptyList<User>())
        private set
    private val callback = AccountManagerCallback<Bundle> {
        when {
            it.isDone -> {
                load()
                ///Toast.makeText(context, "User signed out!", Toast.LENGTH_SHORT).show()
            }
            it.isCancelled -> {
                ///Toast.makeText(context, "Sign out cancelled!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            val accountList = accountManager.accounts.map {
                User(it.name, "")
            }
            userList = accountList
        }
    }

    fun remove(user: User) {
        accountManager.accounts.find { it.name == user.username }?.let {
            accountManager.removeAccount(it, null, callback, null)
        }
    }
}
