package com.metehanbolat.mvicompsoe.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.metehanbolat.mvicompsoe.dummyId
import com.metehanbolat.mvicompsoe.dummySku
import com.metehanbolat.mvicompsoe.favorite.state.FavoriteState

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    uiState: FavoriteState,
    snackbarHostState: SnackbarHostState,
    onFavoriteClick: (sku: String, id: String) -> Unit
) {
    var sku by remember { mutableStateOf(value = dummySku) }
    var id by remember { mutableStateOf(value = dummyId) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { paddingValues: PaddingValues ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                contentAlignment = Alignment.Center,
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                        modifier = Modifier.padding(all = 32.dp)
                    ) {
                        Text(text = "Add Favorite", style = MaterialTheme.typography.headlineLarge)

                        OutlinedTextField(
                            value = sku,
                            onValueChange = { sku = it },
                            label = { Text(text = "Sku") },
                            isError = uiState.error != null,
                            enabled = !uiState.isLoading
                        )

                        OutlinedTextField(
                            value = id,
                            onValueChange = { id = it },
                            label = { Text(text = "Id") },
                            isError = uiState.error != null,
                            enabled = !uiState.isLoading
                        )

                        if (uiState.error != null) {
                            Text(
                                text = uiState.error,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Button(
                            onClick = { onFavoriteClick(sku, id) },
                            enabled = !uiState.isLoading,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (uiState.isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(size = 24.dp),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            } else {
                                Text(text = "Add Favorite")
                            }
                        }
                    }
                }
            )
        }
    )
}