package de.syntax_institut.codingchallenges04_07.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    val username: String,
    val rating: Int
)
