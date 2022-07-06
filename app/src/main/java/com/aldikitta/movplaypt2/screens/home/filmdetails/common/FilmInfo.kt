package com.aldikitta.movplaypt2.screens.home.filmdetails.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.ui.theme.AppBarExpendedHeight
import com.aldikitta.movplaypt2.util.Resource
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun FilmInfo(
    scrollState: LazyListState,
    releaseDate: String,
    overview: String,
    casts: Resource<CreditResponse>,
    navigator: DestinationsNavigator
) {
    Spacer(modifier = Modifier.height(8.dp))

    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Release Date",

                    )
                Text(
                    text = releaseDate,

                    )
                ExpandableText(text = overview)
            }
        }
        item {
            if (casts is Resource.Success) {
                CastDetails(creditsResponse = casts.data!!, navigator = navigator)
            }
        }
    }
}

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 3
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    var expanded by remember {
        mutableStateOf(false)
    }
    val textLayoutResultState = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }
    val seeMoreSizeState = remember {
        mutableStateOf<IntSize?>(null)
    }
    val seeMoreOffsetState = remember {
        mutableStateOf<Offset?>(null)
    }
    // getting raw values for smart cast
    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded && textLayoutResult != null
            && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(
                lastLineIndex
            )
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }
    Box(modifier) {
        Text(
            color = Color.LightGray,
            text = cutText ?: text,
            fontSize = 13.sp,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded = false
                },
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResultState.value = it },
        )
        if (!expanded) {
            val density = LocalDensity.current
            Text(
                // Fixme: Use your app theme color
                text = "... See more",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                onTextLayout = { seeMoreSizeState.value = it.size },
                modifier = Modifier
                    .then(
                        if (seeMoreOffset != null)
                            Modifier.offset(
                                x = with(density) { seeMoreOffset.x.toDp() },
                                y = with(density) { seeMoreOffset.y.toDp() },
                            )
                        else Modifier
                    )
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        expanded = true
                        cutText = null
                    }
                    .alpha(if (seeMoreOffset != null) 1f else 0f)
            )
        }
    }

}

