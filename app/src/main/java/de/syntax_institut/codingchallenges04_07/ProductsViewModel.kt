package de.syntax_institut.codingchallenges04_07

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.codingchallenges04_07.data.database.ProductsDatabase
import de.syntax_institut.codingchallenges04_07.data.model.Product
import de.syntax_institut.codingchallenges04_07.data.model.Rating
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductsViewModel(app: Application) : AndroidViewModel(app) {
    private val productsDao = ProductsDatabase.getDatabase(app).productsDao()
    private val ratingsDao = ProductsDatabase.getDatabase(app).ratingsDao()

    val products = productsDao.getAllItems().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    val productsWithRatings = productsDao.getProductWithRating().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        viewModelScope.launch {
            productsDao.getAllItems().collect { productList ->
                if (productList.isEmpty()) {
                    prepopulate()
                }
            }
        }
    }

    private suspend fun prepopulate() {
        val sampleProducts = listOf(
            Product(name = "Apfel", price = 0.99),
            Product(name = "Banane", price = 1.29),
            Product(name = "Karotte", price = 0.49),
            Product(name = "Tomate", price = 1.09),
            Product(name = "Gurke", price = 0.89),
            Product(name = "Milch", price = 1.19),
            Product(name = "Käse", price = 2.79),
            Product(name = "Brot", price = 1.99),
            Product(name = "Butter", price = 1.49),
            Product(name = "Joghurt", price = 0.69),
            Product(name = "Eier", price = 2.49),
            Product(name = "Saft", price = 1.99)
        )

        sampleProducts.forEach {
            productsDao.insert(it)
            ratingsDao.insert(
                Rating(
                    productId = it.productId,
                    username = "User1",
                    rating = (1..5).random()
                )
            )
            ratingsDao.insert(
                Rating(
                    productId = it.productId,
                    username = "User2",
                    rating = (1..5).random()
                )
            )
        }
    }

    fun add(name: String) = viewModelScope.launch {
        if (name.isNotBlank()) {
            productsDao.insert(Product(name = name, price = 1.0))
        }
    }
}