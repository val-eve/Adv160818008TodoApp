package com.advnmpproj.adv160818008todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.advnmpproj.adv160818008todoapp.util.MIGRATION_1_2
import com.advnmpproj.adv160818008todoapp.util.MIGRATION_1_3
import com.advnmpproj.adv160818008todoapp.util.MIGRATION_2_3

@Database(entities = arrayOf(Todo::class), version = 2)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao

    companion object{
        @Volatile private var instance:TodoDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context:Context) = Room.databaseBuilder(
                context.applicationContext, TodoDatabase::class.java, "tododb"
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_1_3).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}