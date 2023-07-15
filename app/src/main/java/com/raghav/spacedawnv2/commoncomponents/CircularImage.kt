package com.raghav.spacedawnv2.commoncomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raghav.spacedawnv2.ui.theme.spacing

@Composable
fun CircularImage(imageUrl: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.35f)
            .padding(start = MaterialTheme.spacing.small)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .placeholder(com.google.android.material.R.drawable.notification_tile_bg)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .aspectRatio(1f)
                .clip(CircleShape)
                .align(Alignment.Center)
        )
    }
}
