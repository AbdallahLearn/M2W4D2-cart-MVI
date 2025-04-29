package com.example.m2w4d2_mvi_shopping_cart.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m2w4d2_mvi_shopping_cart.presentation.intent.CartIntent
import com.example.m2w4d2_mvi_shopping_cart.presentation.state.CartState
import com.example.m2w4d2_mvi_shopping_cart.presentation.vm.CartViewModel
@Composable
fun CartScreen(viewModel: CartViewModel) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(CartIntent.LoadCart)
    }

    when (state) {
        is CartState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is CartState.Success -> {
            val items = (state as CartState.Success).items
            LazyColumn {
                items(items) { item ->
                    Text(
                        text = "${item.name} - Qty: ${item.quantity} - Price: ${item.price} SAR",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        is CartState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(state as CartState.Error).message}")
            }
        }
    }
}
