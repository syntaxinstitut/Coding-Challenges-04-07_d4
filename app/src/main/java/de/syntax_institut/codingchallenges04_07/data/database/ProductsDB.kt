package de.syntax_institut.codingchallenges04_07.data.database

import android.content.Context
import de.syntax_institut.codingchallenges04_07.data.model.Rating
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntax_institut.codingchallenges04_07.data.model.Product
import kotlin.jvm.java

@Database(entities = [Product::class, Rating::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
    abstract fun ratingsDao(): RatingsDAO

    companion object {
        @Volatile
        private var Instance: ProductsDatabase? = null

        fun getDatabase(context: Context): ProductsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ProductsDatabase::class.java, "myproducts_database")
                    .build().also { Instance = it }
            }
        }
    }
}
