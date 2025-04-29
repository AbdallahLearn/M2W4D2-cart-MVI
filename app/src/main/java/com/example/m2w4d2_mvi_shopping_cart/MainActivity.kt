package com.example.m2w4d2_mvi_shopping_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m2w4d2_mvi_shopping_cart.data.CartRepositoryImpl.CartRepositoryImpl
import com.example.m2w4d2_mvi_shopping_cart.domain.usecase.AddItemToCartUseCase
import com.example.m2w4d2_mvi_shopping_cart.domain.usecase.GetCartItemsUseCase
import com.example.m2w4d2_mvi_shopping_cart.presentation.screen.AddItemScreen
import com.example.m2w4d2_mvi_shopping_cart.presentation.screen.CartScreen
import com.example.m2w4d2_mvi_shopping_cart.presentation.vm.CartViewModel
import com.example.m2w4d2_mvi_shopping_cart.ui.theme.M2W4D2MVIShoppingCartTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Manual DI
        val repository = CartRepositoryImpl()
        val getCartItemsUseCase = GetCartItemsUseCase(repository)
        val addItemToCartUseCase = AddItemToCartUseCase(repository)

        val factory = CartViewModelFactory(getCartItemsUseCase, addItemToCartUseCase)
        val viewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]

        setContent {
            var showCart by remember { mutableStateOf(false) }

            if (showCart) {
                CartScreen(viewModel = viewModel)
            } else {
                AddItemScreen(viewModel = viewModel) {
                    showCart = true
                }
            }
        }
    }
}



class CartViewModelFactory(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(getCartItemsUseCase, addItemToCartUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

