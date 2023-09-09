package com.raghav.launches.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.raghav.designsystem.ui.theme.colors
import com.raghav.designsystem.ui.theme.spacing
import com.raghav.launches.R
import com.raghav.spacedawnv2.domain.model.LaunchDetail
import com.raghav.util.Constants
import com.raghav.util.Helpers.Companion.formatTo
import com.raghav.util.Helpers.Companion.toDate

@Composable
fun LaunchesScreenItem(
    launch: LaunchDetail,
    addReminderClicked: (LaunchDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        launch.image?.let {
            com.raghav.common.components.CircularImage(imageUrl = it, widthFraction = 0.35f)
        }
        LaunchContent(
            launch = launch,
            addReminderClicked = {
                addReminderClicked(it)
            }
        )
    }
}

@Composable
fun LaunchContent(
    launch: LaunchDetail,
    addReminderClicked: (LaunchDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = MaterialTheme.spacing.small
        )
    ) {
        Text(
            text = launch.name.orEmpty(),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = launch.launchServiceProvider?.name.orEmpty(),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(
                R.string.rocket_name,
                launch.rocket?.configuration?.full_name.orEmpty()
            ),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.launch_status) + " ")
                withStyle(
                    style = when (launch.status?.name) {
                        stringResource(R.string.to_be_determined) -> SpanStyle(color = Color.Red)
                        stringResource(R.string.go_for_launch) -> SpanStyle(MaterialTheme.colors.Green)
                        stringResource(R.string.to_be_confirmed) -> SpanStyle(MaterialTheme.colors.Yellow)
                        else -> SpanStyle()
                    }
                ) {
                    append(launch.status?.name.orEmpty())
                }
            },
            style = MaterialTheme.typography.bodyMedium
        )

        if (launch.net.isNotEmpty()) {
            Text(
                text = launch.net.toDate(Constants.LAUNCH_DATE_INPUT_FORMAT).formatTo(
                    Constants.DATE_OUTPUT_FORMAT
                ),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        OutlinedButton(onClick = { addReminderClicked(launch) }) {
            Text(
                text = stringResource(id = R.string.add_reminder)
            )
        }
    }
}
