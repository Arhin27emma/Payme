package com.techgen.payme.ui.bank

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techgen.payme.viewmodel.BankViewModel
import com.techgen.payme.viewmodel.BankAccountItem

@Composable
fun BankScreen(
    viewModel: BankViewModel = viewModel(),
    onAccountClick: (BankAccountItem) -> Unit
) {
    val darkBackground = Color(0xFF181A20)
    val accounts by viewModel.accounts.collectAsState()
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
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Bank Accounts",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                LazyColumn {
                    items(accounts) { account ->
                        BankAccountItem(account = account, onClick = { onAccountClick(account) })
                    }
                }
            }
        }
    }
}

@Composable
fun BankAccountItem(account: BankAccountItem, onClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInHorizontally()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = Color(0xFF23252B))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = account.accountNumber,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Balance: $${account.balance}",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
} 