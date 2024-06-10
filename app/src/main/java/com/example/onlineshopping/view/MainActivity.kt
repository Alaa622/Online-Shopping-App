package com.example.onlineshopping.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopping.R
import com.example.onlineshopping.model.ProductItem
import com.example.onlineshopping.model.base64Decode
import com.example.onlineshopping.ui.theme.OnlineShoppingTheme
import com.example.onlineshopping.viewModel.ProductViewModel
import com.example.onlineshopping.viewModel.ProductViewModelFactory
import com.google.gson.Gson


class MainActivity : ComponentActivity() {
    private val productViewModel by lazy {
        ViewModelProvider(
            this,
            ProductViewModelFactory(application)
        )[ProductViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnlineShoppingTheme {
                AppNavigation(productViewModel = productViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            SharedScreenSection(
                productViewModel = productViewModel,
                modifier = modifier,
                navController
            ) {
                HomeProducts(productViewModel = productViewModel, navController =navController )
            }
        }
        composable("products") {
            SharedScreenSection(
                productViewModel=productViewModel,
                modifier = modifier,
                navController
            ) {
                ProductsByCategory(productViewModel = productViewModel, navController)
            }
        }
        composable("productDetails/{gsonProductItem}") {
            val gsonProductItem = it.arguments?.getString("gsonProductItem")?.base64Decode()
            val productItem = Gson().fromJson(gsonProductItem, ProductItem::class.java)
            SharedScreenSection(
                productViewModel=productViewModel,
                modifier = modifier,
                navController
            ) {
                ShowProductDetails(productItem = productItem)
            }
        }

    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    OnlineShoppingTheme {
//
//    }
//}