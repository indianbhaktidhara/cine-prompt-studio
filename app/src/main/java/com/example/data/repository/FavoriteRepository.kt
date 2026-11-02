package com.example.data.repository

import com.example.data.db.FavoritePromptDao
import com.example.data.db.FavoritePromptEntity
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val dao: FavoritePromptDao) {

    val allFavorites: Flow<List<FavoritePromptEntity>> = dao.getAllFavorites()

    val favoritePlaceIds: Flow<List<String>> = dao.getAllFavoritePlaceIds()

    fun isPlaceFavorited(placeId: String): Flow<Boolean> = dao.isPlaceFavorited(placeId)

    suspend fun saveFavorite(favorite: FavoritePromptEntity): Long {
        return dao.insertFavorite(favorite)
    }

    suspend fun removeFavoriteByPlaceId(placeId: String) {
        dao.deleteFavoriteByPlaceId(placeId)
    }

    suspend fun removeFavorite(favorite: FavoritePromptEntity) {
        dao.deleteFavorite(favorite)
    }

    suspend fun removeFavoriteById(id: Long) {
        dao.deleteFavoriteById(id)
    }
}
