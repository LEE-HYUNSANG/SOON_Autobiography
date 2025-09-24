package com.yourcompany.biography.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BiographyPromptDto(
    @SerialName("id") val id: String,
    @SerialName("question") val question: String,
    @SerialName("category") val category: String? = null
)
