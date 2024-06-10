package com.example.onlineshopping.view


import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onlineshopping.R
import com.example.onlineshopping.ui.theme.OnlineShoppingTheme

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingTopAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home_bar),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon =
        if (navController.currentDestination == navController.findDestination("productDetails/{gsonProductItem}"))
        {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        } else {
            {
                //nothing
            }
        },

        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier.padding(8.dp),

                    )
            }

        }
    )

}

//@Composable
//fun NavigationBottomBar() {
//    NavigationBar {
//        NavigationBarItem(selected = true,
//            onClick = {},
//            icon = {
//                Icon(
//                    imageVector = Icons.Filled.Home,
//                    contentDescription = ""
//                )
//            }, label = { Text(text = stringResource(id = R.string.bottom_bar_home)) })
//        NavigationBarItem(selected = false,
//            onClick = {},
//            icon = {
//                Icon(
//                    imageVector = Icons.Filled.AccountCircle,
//                    contentDescription = ""
//                )
//            }, label = { Text(text = stringResource(R.string.bottom_bar_profile)) })
//        NavigationBarItem(selected = false,
//            onClick = {},
//            icon = {
//                Icon(
//                    imageVector = Icons.Filled.Settings,
//                    contentDescription = ""
//                )
//            }, label = { Text(text = stringResource(id = R.string.bottom_bar_settings)) })
//    }
//}


