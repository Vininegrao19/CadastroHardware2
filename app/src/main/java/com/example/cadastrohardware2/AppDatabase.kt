package com.example.cadastrohardware2
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PecaHardware::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pecaDao(): PecaDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hardware_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}