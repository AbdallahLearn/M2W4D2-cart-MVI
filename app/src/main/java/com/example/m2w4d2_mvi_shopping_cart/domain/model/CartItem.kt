package com.example.m2w4d2_mvi_shopping_cart.domain.model


data class CartItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double
)
