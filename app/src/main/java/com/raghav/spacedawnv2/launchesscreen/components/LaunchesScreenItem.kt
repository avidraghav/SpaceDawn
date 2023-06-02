package com.raghav.spacedawnv2.launchesscreen.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.raghav.spacedawnv2.R
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.spacedawnv2.ui.theme.SpaceDawnTheme

@Composable
fun LaunchesScreenItem(
    launch: LaunchDetail,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {}
) {
    Row(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = launch.image,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.image_of_rocket)
        )
        Column {
            Text(
                text = launch.name.orEmpty(),
                overflow = TextOverflow.Ellipsis
            )
            Text(text = launch.launchServiceProvider?.name.orEmpty())
            Text(text = launch.rocket?.configuration?.full_name.orEmpty())
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun LaunchesScreenItemPreview() {
    SpaceDawnTheme {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = "https://picsum.photos/200/300",
                contentDescription = stringResource(R.string.image_of_rocket)
            )
            Column {
                Text(text = "Falcon 9 Block 5 | Dragon CRS-2 SpX-28")
                Text(text = "SpaceX")
                Text(text = "Falcon 9 Block 5")
            }
        }
    }
}
