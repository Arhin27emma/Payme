package com.techgen.payme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class BankAccountItem(
    val id: String,
    val accountNumber: String,
    val balance: Double
)

class BankViewModel : ViewModel() {
    private val _accounts = MutableStateFlow<List<BankAccountItem>>(emptyList())
    val accounts: StateFlow<List<BankAccountItem>> = _accounts

    init {
        // Simulate loading real data
        _accounts.value = listOf(
            BankAccountItem("1", "1234567890", 1000.0),
            BankAccountItem("2", "0987654321", 2000.0),
            BankAccountItem("3", "1122334455", 3000.0),
            BankAccountItem("4", "5544332211", 4000.0)
        )
    }
} 