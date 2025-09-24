package com.yourcompany.biography.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.biography.R
import com.yourcompany.biography.ui.recorder.RecorderControls
import com.yourcompany.biography.ui.theme.BiographyTheme
import com.yourcompany.biography.ui.wizard.WizardScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartRecording: () -> Unit = {},
    onStopRecording: () -> Unit = {}
) {
    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        Content(
            paddingValues = paddingValues,
            onStartRecording = onStartRecording,
            onStopRecording = onStopRecording
        )
    }
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
        )
        WizardScreen(
            modifier = Modifier.fillMaxWidth(),
            onPromptSelected = {
                // TODO: Connect to navigation or prompt handling logic.
            }
        )
        RecorderControls(
            modifier = Modifier.fillMaxWidth(),
            onStartRecording = onStartRecording,
            onStopRecording = onStopRecording,
            buttonText = stringResource(id = R.string.start_recording),
            buttonContentDescription = stringResource(id = R.string.start_recording_talkback_label)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    BiographyTheme {
        MainScreen()
    }
}
