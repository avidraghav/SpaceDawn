package com.raghav.spacedawnv2.commoncomponent

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.raghav.spacedawnv2.ui.theme.spacing

/**
 * Composable to show Material 3's AlertDialog box
 * @param onDismissClick callback when the dialog is dismissed either by pressing the
 * dismiss/negative button or clicking outside the area of dialog box.
 * @param onConfirmClick callback when the Allow/positive button is clicked.
 * @param title title of the Alert Dialog box.
 * @param content content of the Alert Dialog box.
 * @param modifier optional modifier.
 */
@Composable
fun AlertDialog(
    @StringRes
    title: Int,
    @StringRes
    content: Int,
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    var openDialog: Boolean by remember {
        mutableStateOf(true)
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Callback when the user clicks anywhere outside the dialog
                // or presses the back button
                // note: this is not called when the Dismiss button is clicked
                onDismissClick()
                openDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmClick()
                    }
                ) {
                    Text("Allow")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissClick()
                        openDialog = false
                    }
                ) {
                    Text("Dismiss")
                }
            },
            title = { Text(text = stringResource(title)) },
            text = {
                Text(
                    text = stringResource(content)
                )
            },
            modifier = modifier,
            tonalElevation = MaterialTheme.spacing.small
        )
    }
}
