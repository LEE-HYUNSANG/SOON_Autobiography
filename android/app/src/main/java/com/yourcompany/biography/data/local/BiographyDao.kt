package com.yourcompany.biography.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BiographyDao {
    @Query("SELECT * FROM biography_entries ORDER BY createdAt DESC")
    fun observeEntries(): Flow<List<BiographyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: BiographyEntity)
}
