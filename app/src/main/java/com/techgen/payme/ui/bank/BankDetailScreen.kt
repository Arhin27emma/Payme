package com.techgen.payme.ui.bank

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techgen.payme.viewmodel.BankAccountItem

@Composable
fun BankDetailScreen(
    account: BankAccountItem,
    onBackClick: () -> Unit
) {
    val darkBackground = Color(0xFF181A20)
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically()
            ) {
                Column {
                    Text(
                        text = "Account Number: ${account.accountNumber}",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Balance: $${account.balance}",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
} 