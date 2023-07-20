package com.raghav.spacedawnv2.commoncomponent

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
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.ui.theme.spacing

/**
 * Composable to display a circular Circular Image
 *
 * @param imageUrl Url of the image to be loaded
 * @param widthFraction Percentage of total width of Parent this Composable needs to occupy
 * @param modifier Optional Modifier
 */
@Composable
fun CircularImage(imageUrl: String, widthFraction: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(widthFraction)
            .padding(start = MaterialTheme.spacing.small)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                .error(R.drawable.error_icon)
                .placeholder(R.drawable.notification_icon)
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
