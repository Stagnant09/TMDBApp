package com.example.tmdbapp.ui.screens.movieList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tmdbapp.interactors.MovieInteractor
import com.example.tmdbapp.statics.MovieDetails
import com.example.tmdbapp.ui.components.MovieCardList
import com.example.tmdbapp.ui.components.TabWideCircularProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onBack: () -> Unit,
    onNavigateToMovieDetails: () -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is MovieListContract.Effect.NavigateToMovieDetails -> {
                    onNavigateToMovieDetails()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Movie List")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.setEvent(MovieListContract.Event.Refresh) }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Refresh movie list"
                        )
                    }
                }
            )
        }
    ) {
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { viewModel.setEvent(MovieListContract.Event.Refresh) },
        ) {
            Column(modifier = Modifier.padding(it)) {
                when (state.isLoading) {
                    true -> {
                        TabWideCircularProgressIndicator()
                    }

                    false -> {
                        Text(
                            text = "Search results for \"${state.query}\"",
                            modifier = Modifier.padding(16.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        when (state.movies.isNotEmpty()) {
                            true -> {
                                val reachedBottom: Boolean by remember {
                                    derivedStateOf {
                                        val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
                                        lastVisibleItem?.index != 0 && lastVisibleItem?.index == lazyListState.layoutInfo.totalItemsCount - 1
                                    }
                                }

                                LaunchedEffect(reachedBottom) {
                                    if (reachedBottom) {
                                        viewModel.setEvent(MovieListContract.Event.ScrollDown)
                                    }
                                }
                                MovieCardList(
                                    movies = state.movies,
                                    lazyListState = lazyListState,
                                    onItemClick = { movie ->
                                        MovieDetails.movie = movie
                                        onNavigateToMovieDetails()
                                    }
                                )
                            }
                            false -> {}
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    val viewModel = MovieListViewModel(
        interactor = MovieInteractor(),
        query = "alien"
    )
    MovieListScreen(
        viewModel = viewModel, onBack = {}, onNavigateToMovieDetails = {}
    )
}