package de.syntax_institut.codingchallenges04_07.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithRating(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val ratings: List<Rating>
)