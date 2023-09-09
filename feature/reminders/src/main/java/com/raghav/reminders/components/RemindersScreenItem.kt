package com.raghav.reminders.components

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
import com.raghav.reminders.R
import com.raghav.spacedawnv2.domain.model.Reminder
import com.raghav.util.Constants
import com.raghav.util.Helpers.Companion.formatTo
import com.raghav.util.Helpers.Companion.toDate

@Composable
fun RemindersScreenItem(
    reminder: Reminder,
    cancelReminderClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        reminder.image?.let {
            com.raghav.common.components.CircularImage(imageUrl = it, widthFraction = 0.35f)
        }
        ReminderContent(reminder = reminder, cancelReminderClicked = {
            cancelReminderClicked(it.id)
        })
    }
}

@Composable
fun ReminderContent(
    reminder: Reminder,
    cancelReminderClicked: (Reminder) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = MaterialTheme.spacing.small
        )
    ) {
        Text(
            text = reminder.name.orEmpty(),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.launch_status) + " ")
                withStyle(
                    style = when (reminder.status?.name) {
                        stringResource(R.string.to_be_determined) -> SpanStyle(color = Color.Red)
                        stringResource(R.string.go_for_launch) -> SpanStyle(MaterialTheme.colors.Green)
                        stringResource(R.string.to_be_confirmed) -> SpanStyle(MaterialTheme.colors.Yellow)
                        else -> SpanStyle()
                    }
                ) {
                    append(reminder.status?.name.orEmpty())
                }
            },
            style = MaterialTheme.typography.bodyMedium
        )

        if (reminder.net.isNotEmpty()) {
            Text(
                text = reminder.net.toDate(Constants.LAUNCH_DATE_INPUT_FORMAT).formatTo(
                    Constants.DATE_OUTPUT_FORMAT
                ),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        OutlinedButton(onClick = { cancelReminderClicked(reminder) }) {
            Text(
                text = stringResource(id = R.string.cancel_reminder)
            )
        }
    }
}
