package com.example.walmart

import java.io.Serializable

data class Product(
    val productName: String,
    val productDescription: String,
    val cost: Double,
    val imageId: Int,
    val logoId: Int,
) : Serializable
