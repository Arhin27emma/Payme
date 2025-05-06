package com.techgen.payme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class RequestItem(
    val id: String,
    val title: String,
    val description: String
)

class RequestViewModel : ViewModel() {
    private val _requests = MutableStateFlow<List<RequestItem>>(emptyList())
    val requests: StateFlow<List<RequestItem>> = _requests

    init {
        // Simulate loading real data
        _requests.value = listOf(
            RequestItem("1", "Request 1", "Description for Request 1"),
            RequestItem("2", "Request 2", "Description for Request 2"),
            RequestItem("3", "Request 3", "Description for Request 3"),
            RequestItem("4", "Request 4", "Description for Request 4")
        )
    }
} 