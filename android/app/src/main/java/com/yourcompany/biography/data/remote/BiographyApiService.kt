package com.yourcompany.biography.data.remote

import com.yourcompany.biography.model.BiographyPromptDto
import retrofit2.http.GET

interface BiographyApiService {
    @GET("prompts")
    suspend fun fetchPrompts(): List<BiographyPromptDto>
}
