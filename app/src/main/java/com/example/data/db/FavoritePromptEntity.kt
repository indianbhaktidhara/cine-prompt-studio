package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_prompts")
data class FavoritePromptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val placeId: String,
    val placeName: String,
    val location: String,
    val category: String,
    val directorStyle: String,
    val productionStandard: String,
    val aspectRatio: String,
    val aspectRatioLabel: String,
    val generatedCodeBlock: String,
    val positivePrompt: String,
    val savedAt: Long = System.currentTimeMillis()
)
