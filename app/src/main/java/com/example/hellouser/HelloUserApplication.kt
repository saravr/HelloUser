package com.example.hellouser

import android.accounts.AccountManager
import android.app.Application

class HelloUserApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val accountManager = AccountManager.get(this)
        println("+++++ ACCT SZ: ${accountManager.accounts.size}")
        accountManager.accounts.forEach {
            println("+++++ ACCOUNT: ${it.type} -- ${it.name}")
        }
    }
}
