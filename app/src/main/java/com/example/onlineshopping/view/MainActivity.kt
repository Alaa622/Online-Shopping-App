package com.example.onlineshopping.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopping.R
import com.example.onlineshopping.model.ProductItem
import com.example.onlineshopping.model.base64Decode
import com.example.onlineshopping.ui.theme.OnlineShoppingTheme
import com.example.onlineshopping.viewModel.ProductViewModel
import com.google.gson.Gson


class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()
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
        startDestination = "categories"
    ) {
        composable("categories") {
            HomeScreen(
                modifier = modifier,
                title = R.string.categories_title,
                topAppBarText = R.string.categories_bar,
                navController
            ) {
                CategoriesSection(productViewModel = productViewModel) {
                    navController.navigate("products")
                }
            }
        }
        composable("products") {
            HomeScreen(
                modifier = modifier,
                title = R.string.products_title,
                topAppBarText = R.string.products_bar,
                navController
            ) {
                ProductsSection(productViewModel = productViewModel, navController)
            }
        }
        composable("productDetails/{gsonProductItem}") {
            val gsonProductItem = it.arguments?.getString("gsonProductItem")?.base64Decode()
            val productItem = Gson().fromJson(gsonProductItem, ProductItem::class.java)
            HomeScreen(
                modifier = modifier,
                title = R.string.product_details,
                topAppBarText = R.string.product_details,
                navController
            ) {
                ShowProductDetails(productItem = productItem)
            }
        }

    }
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    @StringRes title: Int,
    @StringRes topAppBarText: Int,
    navController: NavController,
    content: @Composable () -> Unit

) {
    Scaffold(
        topBar = { ShoppingTopAppBar(topAppBarText,navController) },
        bottomBar = { NavigationBottomBar() },

        ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.padding(8.dp))
            SearchBar()
            Text(
                text = stringResource(id = title),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            content()
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