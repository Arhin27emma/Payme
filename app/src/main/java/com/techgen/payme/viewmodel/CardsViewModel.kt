package com.techgen.payme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CardItem(
    val id: String,
    val cardNumber: String,
    val expiryDate: String
)

class CardsViewModel : ViewModel() {
    private val _cards = MutableStateFlow<List<CardItem>>(emptyList())
    val cards: StateFlow<List<CardItem>> = _cards

    init {
        // Simulate loading real data
        _cards.value = listOf(
            CardItem("1", "4000 1234 5678 9010", "09/23"),
            CardItem("2", "4000 1234 5678 9011", "10/23"),
            CardItem("3", "4000 1234 5678 9012", "11/23"),
            CardItem("4", "4000 1234 5678 9013", "12/23")
        )
    }
} 