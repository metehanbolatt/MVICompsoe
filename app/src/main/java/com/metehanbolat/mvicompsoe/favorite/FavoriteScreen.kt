package com.metehanbolat.mvicompsoe.favorite

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metehanbolat.mvicompsoe.favorite.component.FavoriteContent
import com.metehanbolat.mvicompsoe.favorite.effect.FavoriteEffect
import com.metehanbolat.mvicompsoe.favorite.event.FavoriteEvent

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onNavigateToMyList: () -> Unit
) {
    val viewModel = viewModel<FavoriteViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { favoriteEffect: FavoriteEffect ->
            when (favoriteEffect) {
                is FavoriteEffect.NavigateToMyList -> {
                    onNavigateToMyList()
                }

                is FavoriteEffect.ShowErrorMessage -> {
                    snackbarHostState.showSnackbar(
                        message = favoriteEffect.errorMessage,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    FavoriteContent(
        modifier = modifier,
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onFavoriteClick = { sku: String, id: String ->
            viewModel.handleEvent(
                event = FavoriteEvent.OnFavoriteClicked(
                    sku = sku,
                    id = id
                )
            )
        }
    )
}



