package com.techgen.payme.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techgen.payme.viewmodel.CardsViewModel
import com.techgen.payme.R

@Composable
fun WalletScreen(navController: NavController) {
    val darkBackground = Color(0xFF181A20)
    val grayBackground = Color(0xFF23252B)
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Request", "Bank", "Cards")
    val cardsViewModel: CardsViewModel = viewModel()
    val cards by cardsViewModel.cards.collectAsState()
    val currentCard = cards.firstOrNull()

    Scaffold(
        containerColor = darkBackground,
        bottomBar = {
            NavigationBar(
                containerColor = grayBackground,
                contentColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = when (index) {
                                    0 -> Icons.Default.Home
                                    1 -> Icons.Default.Email
                                    2 -> Icons.Default.AccountBox
                                    3 -> Icons.Default.AccountCircle
                                    else -> Icons.Default.Home
                                },
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> navController.navigate("home")
                                1 -> navController.navigate("request")
                                2 -> navController.navigate("bank")
                                3 -> navController.navigate("cards")
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(darkBackground)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
                Text(
                    text = "WALLET",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White
                )
            }
            // Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFFF6A5E), Color(0xFFFF9472))
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CreditCard,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.CreditCard,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.5f),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        // Mastercard logo placeholder
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mastercard),
                            contentDescription = "Mastercard",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Text(
                        text = currentCard?.cardNumber ?: "4000 1234 5678 9010",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "VALID THRU",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                            Text(
                                text = currentCard?.expiryDate ?: "09/23",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "Nikola Stojanovic",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            // Actions
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 18.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(grayBackground)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ActionButton(icon = Icons.Default.Send, label = "SEND")
                    ActionButton(icon = Icons.Default.ArrowForward, label = "TRANSFER")
                    ActionButton(icon = Icons.Default.List, label = "PASSBOOK")
                    ActionButton(icon = Icons.Default.MoreVert, label = "MORE")
                }
            }
            // Transactions
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(grayBackground)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Transactions",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(onClick = { /* TODO: More action */ }) {
                            Text("MORE", color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TransactionItemUI(
                        name = "John Doe",
                        type = "Transferred",
                        amount = "+$201.00",
                        date = "02 April 2019",
                        iconColor = Color(0xFF4CAF50)
                    )
                    TransactionItemUI(
                        name = "Amelia Nelson",
                        type = "Added to Wallet",
                        amount = "+$201.00",
                        date = "02 April 2019",
                        iconColor = Color(0xFF2196F3)
                    )
                    TransactionItemUI(
                        name = "Martin Anderson",
                        type = "Sent",
                        amount = "-$201.00",
                        date = "02 April 2019",
                        iconColor = Color(0xFFF44336)
                    )
                    TransactionItemUI(
                        name = "John Doe",
                        type = "Exchanged",
                        amount = "-$201.00",
                        date = "02 April 2019",
                        iconColor = Color(0xFFFFC107)
                    )
                }
            }
        }
    }
}

@Composable
fun ActionButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = CircleShape,
            color = Color(0xFF181A20),
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun TransactionItemUI(name: String, type: String, amount: String, date: String, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = iconColor
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(name, color = Color.White, fontWeight = FontWeight.Bold)
                Text(type, color = Color.Gray, fontSize = 12.sp)
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(amount, color = if (amount.startsWith("+")) Color(0xFF4CAF50) else Color(0xFFF44336), fontWeight = FontWeight.Bold)
            Text(date, color = Color.Gray, fontSize = 10.sp)
        }
    }
} 