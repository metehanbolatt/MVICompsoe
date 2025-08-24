package com.metehanbolat.mvicompsoe.favorite.event

sealed interface FavoriteEvent {
    data class OnFavoriteClicked(val sku: String, val id: String) : FavoriteEvent
}