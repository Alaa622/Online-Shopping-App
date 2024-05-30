package com.example.onlineshopping.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.bumptech.glide.request.transition.Transition
import com.example.onlineshopping.model.ProductItem
import com.example.onlineshopping.model.base64Encode
import com.example.onlineshopping.viewModel.ProductViewModel
import com.google.gson.Gson

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
private fun ProductListItem(productItem: ProductItem, onItemClicked: () -> Unit) {
    var image by remember {
        mutableStateOf("")
    }
    image = productItem.image

    Card(onClick = { onItemClicked() }) {
        Column() {
            Image(
                painter = rememberImagePainter(data = image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp)
            )
            Text(
                text = productItem.price.toString(),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = productItem.title,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }

}


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductsSection(productViewModel: ProductViewModel, navController: NavController) {
    val products by productViewModel.liveDataProductsOfCategory.observeAsState(initial = emptyList())
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { productItem ->
            ProductListItem(productItem = productItem) {
                val gsonProductItem = Gson().toJson(productItem).base64Encode()
                navController.navigate("productDetails/$gsonProductItem")
            }
        }
    }

}



