package com.yourcompany.biography.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yourcompany.biography.data.repo.BiographyRepository
import com.yourcompany.biography.util.AppLogger
import com.yourcompany.biography.util.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class RecordingWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val repository: BiographyRepository,
    private val logger: AppLogger
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): androidx.work.ListenableWorker.Result {
        logger.i(TAG, "RecordingWorker started")
        return when (val result = repository.fetchPrompts()) {
            is Result.Success -> {
                logger.i(TAG, "Fetched ${result.data.size} prompts for offline cache")
                androidx.work.ListenableWorker.Result.success()
            }
            is Result.Error -> {
                logger.e(TAG, "Failed to refresh prompts", result.throwable)
                androidx.work.ListenableWorker.Result.retry()
            }
        }
    }

    companion object {
        const val TAG = "RecordingWorker"
    }
}
