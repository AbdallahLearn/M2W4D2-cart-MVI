package com.example.m2w4d2_mvi_shopping_cart.data.CartRepositoryImpl

import com.example.m2w4d2_mvi_shopping_cart.data.repository.CartRepository
import com.example.m2w4d2_mvi_shopping_cart.domain.model.CartItem

class CartRepositoryImpl : CartRepository {

    private val cartItems = mutableListOf<CartItem>()

    override suspend fun getCartItems(): List<CartItem> {
        return cartItems
    }


    override suspend fun addItem(item: CartItem) {
        cartItems.add(item)
    }
}
