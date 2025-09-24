package com.yourcompany.biography.model

data class BiographyPrompt(
    val id: String,
    val question: String,
    val category: String? = null
)
