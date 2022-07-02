package com.aldikitta.movplaypt2.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.model.AccountItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
    navController: NavController
) {
    Scaffold {innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.movcolored),
            contentDescription = null
        )
        LazyColumn(
            contentPadding = innerPadding,
            content = {
                val accountItems = listOf(
                    AccountItem(
                        "About",
                        Icons.Filled.Info
                    ),
                    AccountItem(
                        "Rate Us",
                        Icons.Filled.RateReview
                    ),
                    AccountItem(
                        "Share",
                        Icons.Filled.Share
                    ),
                    AccountItem(
                        "Stay connected",
                        Icons.Filled.Chat
                    ),
                )
                items(accountItems){item ->
                    AccountItems(
                        accountItem = item
                    )
                }
            }
        )
    }
}

@Composable
fun AccountItems(
    accountItem: AccountItem,
){}