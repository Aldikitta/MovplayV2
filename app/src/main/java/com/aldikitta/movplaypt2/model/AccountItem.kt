package com.aldikitta.movplaypt2.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

data class AccountItem(
    val title: String,
    val icon: ImageVector
)
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