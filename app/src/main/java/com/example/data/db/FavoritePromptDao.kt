package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePromptDao {

    @Query("SELECT * FROM favorite_prompts ORDER BY savedAt DESC")
    fun getAllFavorites(): Flow<List<FavoritePromptEntity>>

    @Query("SELECT placeId FROM favorite_prompts")
    fun getAllFavoritePlaceIds(): Flow<List<String>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_prompts WHERE placeId = :placeId LIMIT 1)")
    fun isPlaceFavorited(placeId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoritePromptEntity): Long

    @Query("DELETE FROM favorite_prompts WHERE placeId = :placeId")
    suspend fun deleteFavoriteByPlaceId(placeId: String)

    @Delete
    suspend fun deleteFavorite(favorite: FavoritePromptEntity)

    @Query("DELETE FROM favorite_prompts WHERE id = :id")
    suspend fun deleteFavoriteById(id: Long)
}
