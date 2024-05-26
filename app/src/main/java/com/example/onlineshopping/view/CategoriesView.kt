package com.example.onlineshopping.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.onlineshopping.ui.theme.OnlineShoppingTheme
import com.example.onlineshopping.viewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryItemCard(category: String,onItemClicked:()->Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        onClick = { onItemClicked() }
    )
    {
        Text(
            text = category.uppercase(),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Composable
fun CategoriesSection(productViewModel: ProductViewModel, navigateToProducts : () ->Unit) {
    var categories by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    LaunchedEffect(Unit) {
        val observer = Observer<List<String>> { categoryList ->
            categories = categoryList
        }
        productViewModel.liveDataCategories.observeForever(observer)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        items(categories) { category ->
            CategoryItemCard(category = category,
                onItemClicked = {
                    productViewModel.getProductsByCategory(category)
                    navigateToProducts()

            })
        }
    }
}

@Preview
@Composable
private fun CategoryItemCardPreview() {
    OnlineShoppingTheme {
        CategoryItemCard(category = "Category", onItemClicked = {})
    }
}