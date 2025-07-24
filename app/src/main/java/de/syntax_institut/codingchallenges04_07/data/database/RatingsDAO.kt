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

@Dao
interface RatingsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rating: Rating)

    @Query("SELECT * from ratings ORDER BY id ASC")
    fun getAllItems(): Flow<List<Rating>>

    @Delete
    suspend fun delete(rating: Rating)

    @Update
    suspend fun update(rating: Rating)
}