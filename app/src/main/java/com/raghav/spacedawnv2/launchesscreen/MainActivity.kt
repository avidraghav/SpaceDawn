package com.raghav.spacedawnv2.launchesscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raghav.spacedawnv2.ui.theme.SpaceDawnTheme
import com.raghav.spacedawnv2.ui.theme.spacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceDawnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    tonalElevation = MaterialTheme.spacing.small
                ) {
                    LaunchesScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        tonalElevation = MaterialTheme.spacing.small
    ) {
        LaunchesScreen()
    }
}
