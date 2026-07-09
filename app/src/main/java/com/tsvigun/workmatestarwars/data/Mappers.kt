package com.tsvigun.workmatestarwars.data

import com.tsvigun.workmatestarwars.data.local.CharacterEntity
import com.tsvigun.workmatestarwars.data.network.NetworkCharacter
import com.tsvigun.workmatestarwars.domain.StarWarsCharacter

fun NetworkCharacter.toEntity(): CharacterEntity {
    val id = this.url?.trimEnd('/')?.substringAfterLast('/') ?: ""

    return CharacterEntity(
        id = id,
        name = this.name ?: "Unknown",
        birthYear = this.birthYear ?: "Unknown",
        height = this.height ?: "Unknown",
        mass = this.mass ?: "Unknown",
        filmsUrls = this.films?.joinToString(",") ?: ""
    )
}

fun CharacterEntity.toDomain(): StarWarsCharacter {
    return StarWarsCharacter(
        id = this.id,
        name = this.name,
        birthYear = this.birthYear,
        height = this.height,
        mass = this.mass,
        films = emptyList()
    )
}