package com.yourcompany.biography.model

data class BiographyEntry(
    val id: Long,
    val prompt: String,
    val answer: String,
    val createdAt: Long
)
