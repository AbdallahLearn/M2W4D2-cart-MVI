package com.example.m2w4d2_mvi_shopping_cart.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m2w4d2_mvi_shopping_cart.domain.usecase.AddItemToCartUseCase
import com.example.m2w4d2_mvi_shopping_cart.domain.usecase.GetCartItemsUseCase
import com.example.m2w4d2_mvi_shopping_cart.presentation.intent.CartIntent
import com.example.m2w4d2_mvi_shopping_cart.presentation.state.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CartState>(CartState.Loading)
    val state: StateFlow<CartState> = _state

    fun handleIntent(intent: CartIntent) {
        viewModelScope.launch {
            when (intent) {
                is CartIntent.LoadCart -> {
                    _state.value = CartState.Loading
                    try {
                        val items = getCartItemsUseCase()
                        _state.value = CartState.Success(items)
                    } catch (e: Exception) {
                        _state.value = CartState.Error("Failed to load cart")
                    }
                }
                is CartIntent.AddItem -> {
                    try {
                        addItemToCartUseCase(intent.item)
                        handleIntent(CartIntent.LoadCart)
                    } catch (e: Exception) {
                        _state.value = CartState.Error("Failed to add item")
                    }
                }
            }
        }
    }
}

