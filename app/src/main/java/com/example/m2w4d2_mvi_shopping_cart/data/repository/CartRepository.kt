package com.example.m2w4d2_mvi_shopping_cart.data.repository

import com.example.m2w4d2_mvi_shopping_cart.domain.model.CartItem

interface CartRepository {
    suspend fun getCartItems(): List<CartItem>
    suspend fun addItem(item: CartItem)
}
