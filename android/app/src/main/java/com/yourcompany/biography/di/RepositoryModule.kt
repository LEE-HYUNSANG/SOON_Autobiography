package com.yourcompany.biography.di

import com.yourcompany.biography.data.repo.BiographyRepository
import com.yourcompany.biography.data.repo.DefaultBiographyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBiographyRepository(
        repository: DefaultBiographyRepository
    ): BiographyRepository
}
