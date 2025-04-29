package com.example.m2w4d2_mvi_shopping_cart.presentation.state

import com.example.m2w4d2_mvi_shopping_cart.domain.model.CartItem

sealed class CartState {
    object Loading : CartState()
    data class Success(val items: List<CartItem>) : CartState()
    data class Error(val message: String) : CartState()
}
