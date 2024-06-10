package com.example.onlineshopping.view


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
import com.example.onlineshopping.R
import com.example.onlineshopping.viewModel.ProductViewModel

@Composable
fun SharedScreenSection(
    productViewModel: ProductViewModel,
    modifier: Modifier,
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { ShoppingTopAppBar(navController) },
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.padding(4.dp))
            SearchBar()
            CategoriesSection(productViewModel = productViewModel,navController)
            Text(
                text = stringResource(id = R.string.products_title),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleMedium
            )
            content()
        }

    }
}