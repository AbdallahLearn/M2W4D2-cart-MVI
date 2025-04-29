package com.example.m2w4d2_mvi_shopping_cart.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.example.m2w4d2_mvi_shopping_cart.domain.model.CartItem
import com.example.m2w4d2_mvi_shopping_cart.presentation.intent.CartIntent
import com.example.m2w4d2_mvi_shopping_cart.presentation.vm.CartViewModel

@Composable
fun AddItemScreen(viewModel: CartViewModel, onItemAdded: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Add Product")

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") }
        )

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
        )

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
        )

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (name.isNotBlank() && quantity.isNotBlank() && price.isNotBlank()) {
                val item = CartItem(
                    id = (0..999).random(),
                    name = name,
                    quantity = quantity.toInt(),
                    price = price.toDouble()
                )
                viewModel.handleIntent(CartIntent.AddItem(item))
                onItemAdded() // Navigate to Cart
            }
        }) {
            Text("Add to Cart")
        }
    }
}
