package com.example.tmdbapp.ui.screens.movieList

import app.cash.turbine.test
import com.example.tmdbapp.models.Movie
import com.example.tmdbapp.ui.screens.movieList.mock.FakeMovieInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var fakeInteractor: FakeMovieInteractor
    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeInteractor = FakeMovieInteractor()
        viewModel = MovieListViewModel(
            interactor = fakeInteractor,
            query = "roar"
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `LoadMovies updates state with movies`() = runTest(testDispatcher) {
        val fakeMovies = listOf(
            Movie(
                id = 1,
                title = "Roar", //
                overview = "Roar follows a family who are attacked by various African animals at the secluded home of their keeper.\n", //
                releaseDate = "1981-01-01",
                posterPath = "/oN7FZe6qNAAb46MpgbIyua8fJy7.jpg", //
                adult = false,
                backdropPath = null,
                genreIds = listOf(0),
                originalLanguage = "English",
                originalTitle = "Movie Title",
                popularity = 7.0,
                video = true,
                voteAverage = 7.0,
                voteCount = 1000,
            ),
            Movie(
                id = 1,
                title = "Roar", //
                overview = "Roar follows a family who are attacked by various African animals at the secluded home of their keeper.\n", //
                releaseDate = "1981-01-01",
                posterPath = "/oN7FZe6qNAAb46MpgbIyua8fJy7.jpg", //
                adult = false,
                backdropPath = null,
                genreIds = listOf(0),
                originalLanguage = "English",
                originalTitle = "Movie Title",
                popularity = 7.0,
                video = true,
                voteAverage = 7.0,
                voteCount = 1000,
            )
        )
        fakeInteractor.moviesToReturn = fakeMovies

        viewModel.setEvent(MovieListContract.Event.LoadMovies)

        viewModel.uiState.test {
            val loadedState = awaitItem()
            assertEquals(fakeMovies, loadedState.movies)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
