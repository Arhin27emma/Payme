package com.techgen.payme.ui.cards

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
import com.techgen.payme.viewmodel.CardsViewModel
import com.techgen.payme.viewmodel.CardItem

@Composable
fun CardsScreen(
    viewModel: CardsViewModel = viewModel(),
    onCardClick: (CardItem) -> Unit
) {
    val darkBackground = Color(0xFF181A20)
    val cards by viewModel.cards.collectAsState()
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
                text = "Cards",
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
                    items(cards) { card ->
                        CardItem(card = card, onClick = { onCardClick(card) })
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(card: CardItem, onClick: () -> Unit) {
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
                        text = card.cardNumber,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Expires: ${card.expiryDate}",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
} 