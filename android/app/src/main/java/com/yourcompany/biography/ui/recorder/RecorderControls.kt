package com.yourcompany.biography.ui.recorder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.biography.ui.theme.BiographyTheme

@Composable
fun RecorderControls(
    modifier: Modifier = Modifier,
    onStartRecording: () -> Unit = {},
    onStopRecording: () -> Unit = {},
    buttonText: String,
    buttonContentDescription: String? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onStartRecording,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
                .padding(horizontal = 8.dp)
                .let { base ->
                    if (buttonContentDescription != null) {
                        base.semantics { contentDescription = buttonContentDescription }
                    } else {
                        base
                    }
                },
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.titleLarge
            )
        }
        // Future stop/pause controls can be added here.
    }
}

@Preview(showBackground = true)
@Composable
private fun RecorderControlsPreview() {
    BiographyTheme {
        RecorderControls(buttonText = "녹음 시작", buttonContentDescription = "녹음 시작 버튼")
    }
}
