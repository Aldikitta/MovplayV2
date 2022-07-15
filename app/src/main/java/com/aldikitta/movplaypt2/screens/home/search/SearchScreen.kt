package com.aldikitta.movplaypt2.screens.home.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.model.Genre
import com.aldikitta.movplaypt2.model.Search
import com.aldikitta.movplaypt2.screens.commons.MovplayToolbar
import com.aldikitta.movplaypt2.screens.destinations.MovieDetailsScreenDestination
import com.aldikitta.movplaypt2.screens.destinations.TvShowDetailsScreenDestination
import com.aldikitta.movplaypt2.screens.home.HomeViewModel
import com.aldikitta.movplaypt2.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import retrofit2.HttpException
import java.io.IOException

@OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class)
@Destination(start = false)
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchResult = viewModel.searchSearch.value.collectAsLazyPagingItems()
//    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        Modifier.fillMaxSize()
    ) {
        MovplayToolbar(
            navigator = navigator,
            title = {
                SearchBar(
                    viewModel = viewModel,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(67.dp)
//                        .padding(8.dp),
                    onSearch = { searchParam ->
                        viewModel.searchAll(searchParam)
                    }
                )
//                Text(
//                    text = "Search",
//                )
            },
//            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                content = {
                    items(searchResult.itemCount) { index ->
                        searchResult[index]?.let { search ->
                            SearchItem(
                                search,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp)
                                    .padding(4.dp),
                                onClick = {
                                    when (search?.mediaType) {
                                        "movie" -> {
                                            navigator.navigate(MovieDetailsScreenDestination(search.id!!))
                                        }
                                        "tv" -> {
                                            navigator.navigate(TvShowDetailsScreenDestination(search.id!!))
                                        }
                                        else -> {
                                            return@SearchItem
                                        }
                                    }
                                }
                            )
                        }

                    }

                    if (searchResult.loadState.append == LoadState.Loading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            )

//            LazyColumn(
//                contentPadding = PaddingValues(8.dp),
//                verticalArrangement = Arrangement.Top
//            ) {
//                items(searchResult) { search ->
//                    SearchItem(
//                        search,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(130.dp)
//                            .padding(4.dp),
//                        onClick = {
//                            when (search?.mediaType) {
//                                "movie" -> {
//                                    navigator.navigate(MovieDetailsScreenDestination(search.id!!))
//                                }
//                                "tv" -> {
//                                    navigator.navigate(TvShowDetailsScreenDestination(search.id!!))
//                                }
//                                else -> {
//                                    return@SearchItem
//                                }
//                            }
//                        }
//                    )
//                }
//
//                if (searchResult.loadState.append == LoadState.Loading) {
//                    item {
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentWidth(Alignment.CenterHorizontally)
//                        )
//                    }
//                }
//            }

            searchResult.apply {
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        if (searchResult.itemCount <= 0) {
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(250.dp),
                                    painter = painterResource(id = R.drawable.ic_empty_cuate),
                                    contentDescription = null
                                )
                            }
                        }
//                        CircularProgressIndicator(
//                            modifier = Modifier.align(Alignment.Center),
//                            color = Color.Red,
//                            strokeWidth = 2.dp
//                        )
                    }
                    is LoadState.Error -> {
                        val e = searchResult.loadState.refresh as LoadState.Error
                        Text(
                            text = when (e.error) {
                                is HttpException -> {
                                    "Type Something!"
                                }
                                is IOException -> {
                                    "Couldn't reach server, check your internet connection!"
                                }
                                else -> {
                                    "Unknown error occurred"
                                }
                            },
                            modifier = Modifier
                                .padding(12.dp),
                            textAlign = TextAlign.Center,
                        )
                    }

                    is LoadState.NotLoading -> {
                        if (searchResult.itemCount <= 0) {
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(250.dp),
                                    painter = painterResource(id = R.drawable.ic_empty_cuate),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {

    var searchTerm = viewModel.searchTerm.value
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
//            .focusRequester(focusRequester),
        value = text,
        onValueChange = {
            text = it
            viewModel.setSearchTerm(it)
            viewModel.searchAll(it)
        },
        placeholder = {
            Text(
                text = "Search...",
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSearch(searchTerm)
                keyboardController?.hide()
            }
        ),
        maxLines = 1,
        singleLine = true,
        trailingIcon = {
            if (text.trim().isNotEmpty()) {
                IconButton(onClick = {
                    text = ""
                }) {
                    Icon(Icons.Filled.Clear, contentDescription = "abort")
                }
            } else {
                IconButton(onClick = {
                    onSearch(searchTerm)
                }) {
                    Icon(Icons.Filled.Search, contentDescription = "abort")
                }
            }
//            IconButton(onClick = { onSearch(searchTerm) }) {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = null
//                )
//            }
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
//    LaunchedEffect(Unit) {
//        focusRequester.requestFocus()
//    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem(
    search: Search?,
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "${Constants.IMAGE_BASE_URL}/${search?.posterPath}")
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.placeholder)
                    crossfade(true)
                }).build()
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(180.dp)
            .padding(5.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick()
            }
    )
//    Card(
//        modifier = modifier
//            .clickable {
//                onClick()
//            },
//    ) {
//        Row {
//            Image(
//                painter = rememberAsyncImagePainter(
//                    ImageRequest.Builder(LocalContext.current)
//                        .data(data = "${Constants.IMAGE_BASE_URL}/${search?.posterPath}")
//                        .apply(block = fun ImageRequest.Builder.() {
//                            placeholder(R.drawable.placeholder)
//                            crossfade(true)
//                        }).build()
//                ),
//                modifier = Modifier
//                    .fillMaxWidth(0.3f),
//                contentScale = ContentScale.Crop,
//                contentDescription = null
//            )
//
//            Column(
//                modifier = modifier
//                    .fillMaxWidth(0.7f)
//                    .padding(8.dp)
//            ) {
//
//                Text(
//                    text = (search?.name ?: search?.originalName ?: search?.originalTitle
//                    ?: "No title provided"),
//                    fontWeight = FontWeight.Bold,
//                    style = MaterialTheme.typography.titleMedium
//                )
//
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                (search?.firstAirDate ?: search?.releaseDate)?.let {
//                    Text(
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Right,
//                        text = it,
//                        fontWeight = FontWeight.SemiBold,
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                LazyRow(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//
//                    val moviesGenres = homeViewModel.moviesGenres.value
//                    val seriesGenres = homeViewModel.tvShowsGenres.value
//
//                    var searchGenres: List<Genre> = emptyList()
//                    if (search?.mediaType == "tv") {
//                        searchGenres = seriesGenres.filter {
//                            search.genreIds?.contains(it.id)!!
//                        }
//                    }
//                    if (search?.mediaType == "movie") {
//                        searchGenres = moviesGenres.filter {
//                            search.genreIds?.contains(it.id)!!
//                        }
//                    }
//
//                    items(searchGenres) { genre ->
//                        Text(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(end = 5.dp),
//                            text = genre.name,
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    text = search?.overview ?: "No description",
//                    maxLines = 3,
//                    overflow = TextOverflow.Ellipsis,
//                )
//            }
//        }
//    }
}