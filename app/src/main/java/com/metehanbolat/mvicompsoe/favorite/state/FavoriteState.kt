package com.metehanbolat.mvicompsoe.favorite.state

data class FavoriteState(
    val isLoading: Boolean = false,
    val error: String? = null
)