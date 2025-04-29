package com.example.m2w4d2_mvi_shopping_cart.presentation.intent

import com.example.m2w4d2_mvi_shopping_cart.domain.model.CartItem

sealed class CartIntent {
    object LoadCart : CartIntent()
    data class AddItem(val item: CartItem) : CartIntent()
}