package com.raghav.spacedawnv2.launchesscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.launchesscreen.components.LaunchesScreenItem

@Composable
fun LaunchesScreen(
    modifier: Modifier = Modifier,
    viewModel: LaunchesScreenVM = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            is LaunchesScreenState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.launches) { launch ->
                        LaunchesScreenItem(launch = launch)
                    }
                }
            }

            is LaunchesScreenState.Error -> {
                Text(
                    text = state.errorMessage.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(16.dp)

                )
            }

            LaunchesScreenState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            LaunchesScreenState.Empty -> {
                Text(
                    text = stringResource(R.string.launches_screen_text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(16.dp)

                )
            }
        }
    }
}
