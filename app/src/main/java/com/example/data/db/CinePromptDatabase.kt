package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoritePromptEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CinePromptDatabase : RoomDatabase() {

    abstract fun favoritePromptDao(): FavoritePromptDao

    companion object {
        @Volatile
        private var INSTANCE: CinePromptDatabase? = null

        fun getDatabase(context: Context): CinePromptDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CinePromptDatabase::class.java,
                    "cineprompt_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
