package com.yourcompany.biography.data.repo

import com.yourcompany.biography.model.BiographyEntry
import com.yourcompany.biography.model.BiographyPrompt
import com.yourcompany.biography.util.Result
import kotlinx.coroutines.flow.Flow

interface BiographyRepository {
    val recordedEntries: Flow<List<BiographyEntry>>

    suspend fun saveEntry(prompt: String, answer: String): Result<Unit>

    suspend fun fetchPrompts(): Result<List<BiographyPrompt>>
}
