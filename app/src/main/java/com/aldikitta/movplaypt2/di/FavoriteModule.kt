package com.aldikitta.movplaypt2.di

import android.app.Application
import androidx.room.Room
import com.aldikitta.movplaypt2.data.local.FavoritesDatabase
import com.aldikitta.movplaypt2.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {
    @Singleton
    @Provides
    fun provideFavoriteDatabase(application: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoritesDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


}