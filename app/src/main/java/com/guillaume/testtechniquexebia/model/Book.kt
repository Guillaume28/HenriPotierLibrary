package com.guillaume.testtechniquexebia.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @field:PrimaryKey
    val isbn: String,
    val title: String,
    val price: Int,
    val cover: String,
    val isInCart: Boolean = false
)