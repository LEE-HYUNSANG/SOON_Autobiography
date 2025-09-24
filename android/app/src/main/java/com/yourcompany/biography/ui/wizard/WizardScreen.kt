package com.yourcompany.biography.ui.wizard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.biography.ui.theme.BiographyTheme

@Composable
fun WizardScreen(
    modifier: Modifier = Modifier,
    prompts: List<String> = samplePrompts,
    onPromptSelected: (String) -> Unit
) {
    var selectedPrompt by remember(prompts) { mutableStateOf(prompts.firstOrNull().orEmpty()) }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "오늘의 질문",
                style = MaterialTheme.typography.titleLarge
            )
            prompts.take(MAX_VISIBLE_PROMPTS).forEach { prompt ->
                WizardPromptItem(
                    prompt = prompt,
                    isSelected = prompt == selectedPrompt,
                    onClick = {
                        selectedPrompt = prompt
                        onPromptSelected(prompt)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WizardPromptItem(
    prompt: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val background = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surface
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = background),
        onClick = onClick
    ) {
        Text(
            text = prompt,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

private const val MAX_VISIBLE_PROMPTS = 3

private val samplePrompts = listOf(
    "가장 기억에 남는 어린 시절 추억은 무엇인가요?",
    "당신에게 가장 큰 영향을 준 사람은 누구인가요?",
    "지금의 당신을 만든 결정적인 순간은 언제였나요?"
)

@Preview(showBackground = true)
@Composable
private fun WizardScreenPreview() {
    BiographyTheme {
        WizardScreen(onPromptSelected = {})
    }
}
