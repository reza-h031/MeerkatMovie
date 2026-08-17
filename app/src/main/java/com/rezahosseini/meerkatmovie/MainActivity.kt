package com.rezahosseini.meerkatmovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rezahosseini.meerkatmovie.model.local.database.MovieDatabase
import com.rezahosseini.meerkatmovie.model.local.repository.MovieRepositoryLocal
import com.rezahosseini.meerkatmovie.repository.MovieRepository
import com.rezahosseini.meerkatmovie.ui.theme.MeerkatMovieTheme
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModel
import com.rezahosseini.meerkatmovie.viewmodel.MovieViewModelLocal
import com.rezahosseini.meerkatmovie.viewmodel.factory.MovieViewModelFactory
import com.rezahosseini.meerkatmovie.viewmodel.factory.MovieViewModelFactoryLocal
import com.rezahosseini.meerkatmovie.viewmodel.state.MovieUiStateLocal
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // ArrayList
        val repository = MovieRepository()
        val arrayFactory = MovieViewModelFactory(repository)

        // Room
        val database = MovieDatabase.MovieDatabaseProvider.getDatabase(applicationContext)

        val localRepository = MovieRepositoryLocal(
            database.movieDao()
        )

        val localFactory = MovieViewModelFactoryLocal(
            localRepository
        )
        setContent {
            MeerkatMovieTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setUi(modifier = Modifier.padding(innerPadding), arrayFactory = arrayFactory, localFactory = localFactory)
                }
            }
        }
    }
}
@Composable
fun setUi(modifier: Modifier,arrayFactory: MovieViewModelFactory,localFactory: MovieViewModelFactoryLocal){
    Column(modifier) {
//        setViewModelArray(modifier,arrayFactory)
        setViewModelLocal(modifier)
    }
}

@Composable
fun setViewModelArray( modifier: Modifier = Modifier,factory: MovieViewModelFactory) {
    var nameNewMovie by remember {
        mutableStateOf("")
    }
    var yearNewMovie by remember {
        mutableStateOf("")

    }
    var yearError by remember {
        mutableStateOf(false)
    }
    var nameError by remember {
        mutableStateOf(false)
    }
    val viewModel: MovieViewModel = viewModel(
        factory = factory
    )
    val uiState by viewModel.uiState.collectAsState()
    Column (modifier = modifier){
        Column {
            OutlinedTextField(
                value = yearNewMovie,
                onValueChange = {
                    yearNewMovie=it
                    yearError=false
                },
                label = {
                    Text("نام")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = yearError,
                supportingText={
                    if (yearError){
                        Text(text = "فیلد خالی است ")
                    }
                }

            )
            OutlinedTextField(
                value = nameNewMovie,
                onValueChange = {
                    nameNewMovie = it
                    nameError=false
                },
                label = {
                    Text("سال")
                },
                isError = nameError,
                supportingText = {
                    if (nameError){
                        Text(text = "فیلد خالی است ")
                    }
                }
            )

        }
        Button(
            onClick = {
                val nameIsValid = nameNewMovie.isNotBlank()
                val year = yearNewMovie.toIntOrNull()

                nameError = !nameIsValid
                yearError = year == null

                if (year == null || year !in 1800..2100) {
                    yearError = true
                } else if (nameNewMovie.isBlank()) {
                    nameError = true
                } else {
                    viewModel.addNewMovies(
                        nameNewMovie,
                        year
                    )
                }
            }
        ) {
            Text("Add Movie")
        }
        LazyColumn {
            if (uiState.isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }
            uiState.error?.let { error ->
                item{
                    Text(text = error)
                }
            }
            items(uiState.movies) { movie ->
                Text(text = "${movie.name} - ${movie.year}")
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn {
                items(uiState.movies) { movie ->
                    Text("${movie.name} - ${movie.year}")
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator()
            }

            uiState.error?.let { error ->
                Text(text = error)
            }
        }
    }



}
@Composable
fun setViewModelLocal( modifier: Modifier = Modifier,viewModel: MovieViewModelLocal = hiltViewModel()) {
    var nameNewMovie by remember {
        mutableStateOf("")
    }
    var yearNewMovie by remember {
        mutableStateOf("")

    }
    var yearError by remember {
        mutableStateOf(false)
    }
    var nameError by remember {
        mutableStateOf(false)
    }
    val uiState by viewModel.uiState.collectAsState()
    Column (modifier = modifier){
        Column {
            OutlinedTextField(
                value = yearNewMovie,
                onValueChange = {
                    yearNewMovie=it
                    yearError=false
                },
                label = {
                    Text("نام")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = yearError,
                supportingText={
                    if (yearError){
                        Text(text = "فیلد خالی است ")
                    }
                }

            )
            OutlinedTextField(
                value = nameNewMovie,
                onValueChange = {
                    nameNewMovie = it
                    nameError=false
                },
                label = {
                    Text("سال")
                },
                isError = nameError,
                supportingText = {
                    if (nameError){
                        Text(text = "فیلد خالی است ")
                    }
                }
            )

        }
        Button(
            onClick = {
                val nameIsValid = nameNewMovie.isNotBlank()
                val year = yearNewMovie.toIntOrNull()
                nameError = !nameIsValid
                yearError = year == null


                if (year == null || year !in 1800..2100) {
                    yearError = true
                } else if (nameNewMovie.isBlank()) {
                    nameError = true
                } else {
                    viewModel.insert(
                        nameNewMovie,
                        year
                    )
                }
            }
        ) {
            Text("Add Movie")
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when (val state=uiState){
                is MovieUiStateLocal.loading->{
                    CircularProgressIndicator()
                }
                is MovieUiStateLocal.Success->{
                    LazyColumn {
                        items(state.data) { movie ->
                            Text("${movie.name} - ${movie.year}")
                        }
                    }
                }
                is MovieUiStateLocal.Error->{
                    Text(
                        text = state.message
                    )
                }

            }

        }
    }



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeerkatMovieTheme {
    }
}