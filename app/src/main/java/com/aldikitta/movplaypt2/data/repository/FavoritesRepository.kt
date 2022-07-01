package com.aldikitta.movplaypt2.data.repository

import androidx.lifecycle.LiveData
import com.aldikitta.movplaypt2.data.local.Favorite
import com.aldikitta.movplaypt2.data.local.FavoritesDatabase
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val database: FavoritesDatabase) {
    suspend fun insertFavorite(favorite: Favorite) {
        database.dao.insertFavorite(favorite)
    }

    fun getFavorite(): LiveData<List<Favorite>> {
        return database.dao.getAllFavorites()
    }

    fun getAFavorite(mediaId: Int): LiveData<Favorite?> {
        return database.dao.getAFavorite(mediaId)
    }

    fun isFavorite(mediaId: Int): LiveData<Boolean> {
        return database.dao.isFavorite(mediaId)
    }

    suspend fun deleteOneFavorite(favorite: Favorite) {
        database.dao.deleteAFavorite(favorite)
    }

    suspend fun deleteAllFavorites() {
        database.dao.deleteAllFavorites()
    }
}