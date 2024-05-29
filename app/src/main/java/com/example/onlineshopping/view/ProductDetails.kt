package com.example.onlineshopping.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.onlineshopping.R
import com.example.onlineshopping.model.ProductItem
import com.example.onlineshopping.ui.theme.OnlineShoppingTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowProductDetails(productItem: ProductItem) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = rememberImagePainter(data = productItem.image),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .fillMaxWidth()
                .size(height = 200.dp, width = 200.dp)

        )
        Text(
            text = productItem.price.toString(),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = productItem.title,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = productItem.description,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
            Text(
                text = stringResource(id = R.string.add_to_cart_button),
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.labelLarge
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
private fun ShowProductDetailsPreview() {
    OnlineShoppingTheme {
        //ShowProductDetails()
    }
}