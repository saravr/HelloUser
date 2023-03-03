package com.example.hellouser.viewmodel

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellouser.R
import com.example.hellouser.model.User
import kotlinx.coroutines.launch

class AccountsViewModel(application: Application): AndroidViewModel(application) {
    private val accountManager = AccountManager.get(application)
    private val accountType = application.getString(R.string.account_type)
    var userList by mutableStateOf(emptyList<User>())
        private set
    private val callback = AccountManagerCallback<Bundle> {
        when {
            it.isDone -> {
                load()
            }
            it.isCancelled -> {
            }
        }
    }

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            val accountList = accountManager.accounts.map {
                User(it.name, "", "")
            }
            userList = accountList
        }
    }

    fun add(user: User) {
        val account = Account(user.username, accountType)
        if (accountManager.addAccountExplicitly(account, user.password, null)) {
            accountManager.setAuthToken(account, "TOKEN_TYPE", user.authToken)
        } else {
            throw java.lang.IllegalStateException("Failed to add user: ${user.username}")
        }
    }

    fun remove(user: User) {
        accountManager.accounts.find { it.name == user.username }?.let {
            accountManager.removeAccount(it, null, callback, null)
        }
    }
}
