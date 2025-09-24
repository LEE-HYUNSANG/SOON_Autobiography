package com.yourcompany.biography.data.repo

import com.yourcompany.biography.data.local.BiographyDao
import com.yourcompany.biography.data.local.BiographyEntity
import com.yourcompany.biography.data.remote.BiographyApiService
import com.yourcompany.biography.di.IoDispatcher
import com.yourcompany.biography.model.BiographyEntry
import com.yourcompany.biography.model.BiographyPrompt
import com.yourcompany.biography.model.BiographyPromptDto
import com.yourcompany.biography.util.AppLogger
import com.yourcompany.biography.util.DateTimeUtils
import com.yourcompany.biography.util.Result
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
class DefaultBiographyRepository @Inject constructor(
    private val biographyDao: BiographyDao,
    private val apiService: BiographyApiService,
    private val dateTimeUtils: DateTimeUtils,
    private val logger: AppLogger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BiographyRepository {

    override val recordedEntries: Flow<List<BiographyEntry>> =
        biographyDao.observeEntries().map { entities ->
            entities.map { entity ->
                BiographyEntry(
                    id = entity.id,
                    prompt = entity.prompt,
                    answer = entity.answer,
                    createdAt = entity.createdAt
                )
            }
        }

    override suspend fun saveEntry(prompt: String, answer: String): Result<Unit> =
        withContext(dispatcher) {
            try {
                val timestamp = System.currentTimeMillis()
                logger.i(TAG, "Saving entry at ${dateTimeUtils.format(timestamp)}")
                biographyDao.insertEntry(
                    BiographyEntity(
                        prompt = prompt,
                        answer = answer,
                        createdAt = timestamp
                    )
                )
                Result.Success(Unit)
            } catch (throwable: Throwable) {
                logger.e(TAG, "Failed to save entry", throwable)
                Result.Error(throwable)
            }
        }

    override suspend fun fetchPrompts(): Result<List<BiographyPrompt>> =
        withContext(dispatcher) {
            try {
                val prompts = apiService.fetchPrompts().map { it.toDomain() }
                Result.Success(prompts)
            } catch (throwable: Throwable) {
                logger.e(TAG, "Failed to load prompts", throwable)
                Result.Error(throwable)
            }
        }

    private fun BiographyPromptDto.toDomain(): BiographyPrompt =
        BiographyPrompt(id = id, question = question, category = category)

    private companion object {
        const val TAG = "BiographyRepository"
    }
}
