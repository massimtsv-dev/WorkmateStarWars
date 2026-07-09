package com.tsvigun.workmatestarwars.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val birthYear: String,
    val height: String,
    val mass: String,
    val filmsUrls: String
)
