package de.syntax_institut.codingchallenges04_07.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "myproducts")
data class Product(

    @PrimaryKey
    val productId: String = UUID.randomUUID().toString(),
    val name: String,
    val price: Double
)
