package com.tsvigun.workmatestarwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
