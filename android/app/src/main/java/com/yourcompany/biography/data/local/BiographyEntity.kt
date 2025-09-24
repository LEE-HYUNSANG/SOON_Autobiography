package com.yourcompany.biography.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "biography_entries")
data class BiographyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val prompt: String,
    val answer: String,
    val createdAt: Long
)
