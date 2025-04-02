package de.syntax_institut.codingchallenges04_07.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import de.syntax_institut.codingchallenges04_07.data.model.Product
import de.syntax_institut.codingchallenges04_07.data.model.ProductWithRating
import de.syntax_institut.codingchallenges04_07.data.model.Rating
import kotlinx.coroutines.flow.Flow

