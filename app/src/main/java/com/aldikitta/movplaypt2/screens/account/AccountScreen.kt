package com.aldikitta.movplaypt2.screens.account

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.model.AccountItem
import com.aldikitta.movplaypt2.model.accountItems
import com.aldikitta.movplaypt2.screens.destinations.AboutScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val showSocialsDialog = remember { mutableStateOf(false) }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.movcolored),
                contentDescription = null
            )
            LazyColumn(
                contentPadding = innerPadding,
                content = {
                    items(accountItems) { item ->
                        AccountItems(
                            accountItem = item,
                            onClick = {
                                when (item.title) {
                                    "About" -> {
                                        navigator.navigate(AboutScreenDestination)
                                    }
                                    "Rate Us" -> {
                                        //Use This block of code when app already uploaded to Play Store
//                                    val rateIntent = Intent(
//                                        Intent.ACTION_VIEW,
//                                        Uri.parse("market://details?id=" + context.packageName)
//                                    )
//                                    startActivity(context, rateIntent, null)

                                        //Use This block of code when app not yet uploaded to Play Store
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                "This is rate"
                                            )
                                        }
                                    }
                                    "Share" -> {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                "This is share"
                                            )
                                        }
                                    }
                                    "Stay connected" -> {
                                        showSocialsDialog.value = true
                                    }
                                }
                            }
                        )
                    }
                }
            )
        }


    }
}

@Composable
fun AccountItems(
    accountItem: AccountItem,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
        ) {
        Icon(
            imageVector = accountItem.icon,
            contentDescription = accountItem.title
        )
//        Spacer(modifier = Modifier.width(16.dp))
        Text(text = accountItem.title)
    }
}