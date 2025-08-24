package com.metehanbolat.mvicompsoe.favorite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.metehanbolat.mvicompsoe.MVIViewModel
import com.metehanbolat.mvicompsoe.dummyId
import com.metehanbolat.mvicompsoe.dummySku
import com.metehanbolat.mvicompsoe.favorite.effect.FavoriteEffect
import com.metehanbolat.mvicompsoe.favorite.event.FavoriteEvent
import com.metehanbolat.mvicompsoe.favorite.state.FavoriteState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteViewModel(savedStateHandle: SavedStateHandle) :
    MVIViewModel<FavoriteState, FavoriteEvent, FavoriteEffect>(
        savedStateHandle = savedStateHandle
    ) {
    override fun initialState(savedStateHandle: SavedStateHandle): FavoriteState {
        return FavoriteState()
    }

    override fun handleEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.OnFavoriteClicked -> {
                addFavorite(sku = event.sku, id = event.id)
            }
        }
    }

    private fun addFavorite(sku: String, id: String) {
        viewModelScope.launch(
            block = {
                updateState(block = { copy(isLoading = true, error = null) })
                delay(timeMillis = 2_000)
                if (sku == dummySku && id == dummyId) {
                    updateState(block = { copy(isLoading = false) })
                    sendEffect(effect = FavoriteEffect.NavigateToMyList)
                } else {
                    val errorMessage = "Something went wrong!"
                    updateState(block = { copy(isLoading = false, error = errorMessage) })
                    sendEffect(
                        effect = FavoriteEffect.ShowErrorMessage(
                            errorMessage = errorMessage
                        )
                    )
                }
            }
        )
    }
}






