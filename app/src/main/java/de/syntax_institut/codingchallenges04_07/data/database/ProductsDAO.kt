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
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("SELECT * from myproducts ORDER BY productId ASC")
    fun getAllItems(): Flow<List<Product>>

    @Transaction
    @Query("SELECT * FROM myproducts")
    fun getProductWithRating(): Flow<List<ProductWithRating>>

    @Delete
    suspend fun delete(product: Product)

    @Update
    suspend fun update(product: Product)

}
