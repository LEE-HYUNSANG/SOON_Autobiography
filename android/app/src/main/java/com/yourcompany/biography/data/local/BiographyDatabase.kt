package com.yourcompany.biography.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BiographyEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BiographyDatabase : RoomDatabase() {
    abstract fun biographyDao(): BiographyDao
}
