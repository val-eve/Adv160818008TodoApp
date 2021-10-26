package com.advnmpproj.adv160818008todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.advnmpproj.adv160818008todoapp.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDB(context:Context):TodoDatabase{
    val db= Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_1_3).build()
    return db
}

val MIGRATION_1_2 = object: Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }
}

val MIGRATION_2_3 = object: Migration(2, 3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}

val MIGRATION_1_3 = object: Migration(1, 3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}