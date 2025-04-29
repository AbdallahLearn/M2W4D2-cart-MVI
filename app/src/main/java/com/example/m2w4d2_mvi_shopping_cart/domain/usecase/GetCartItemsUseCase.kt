package com.example.m2w4d2_mvi_shopping_cart.domain.usecase

import com.example.m2w4d2_mvi_shopping_cart.data.repository.CartRepository
import com.example.m2w4d2_mvi_shopping_cart.domain.model.CartItem

class GetCartItemsUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(): List<CartItem> = repository.getCartItems()
}
