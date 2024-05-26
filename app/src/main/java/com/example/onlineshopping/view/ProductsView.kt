package com.example.onlineshopping.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.onlineshopping.model.ProductItem
import com.example.onlineshopping.viewModel.ProductViewModel
import com.skydoves.landscapist.rememberDrawablePainter


@SuppressLint("SuspiciousIndentation")
@Composable
private fun ProductListItem(productItem: ProductItem) {
    var image by remember {
        mutableStateOf<Drawable?>(null)
    }
    image = glideBuilder(LocalContext.current, productItem.image)
    Card {
        Column() {
            Image(
                painter = rememberDrawablePainter(drawable = image),
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
fun ProductsSection(productViewModel: ProductViewModel) {
    val products by productViewModel.liveDataProducts.observeAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { productItem ->
            ProductListItem(productItem = productItem)
        }
    }

}

private fun glideBuilder(context: Context, imageUrl: String): Drawable? {
    var image: Drawable? = null
    Glide.with(context).load(imageUrl).into(object : CustomTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            image = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {
            image = placeholder
        }

    })
    return image
}

