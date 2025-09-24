package com.yourcompany.biography.di

import android.content.Context
import androidx.room.Room
import com.yourcompany.biography.data.local.BiographyDao
import com.yourcompany.biography.data.local.BiographyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BiographyDatabase =
        Room.databaseBuilder(
            context,
            BiographyDatabase::class.java,
            "biography.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideBiographyDao(database: BiographyDatabase): BiographyDao =
        database.biographyDao()
}
