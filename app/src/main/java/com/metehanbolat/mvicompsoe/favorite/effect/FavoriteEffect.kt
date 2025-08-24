package com.metehanbolat.mvicompsoe.favorite.effect

sealed interface FavoriteEffect {
    data object NavigateToMyList : FavoriteEffect
    data class ShowErrorMessage(val errorMessage: String) : FavoriteEffect
}