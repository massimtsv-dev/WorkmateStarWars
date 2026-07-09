package com.tsvigun.workmatestarwars.data.repository

import com.tsvigun.workmatestarwars.data.local.CharacterDao
import com.tsvigun.workmatestarwars.data.network.SwapiApi
import com.tsvigun.workmatestarwars.data.toDomain
import com.tsvigun.workmatestarwars.data.toEntity
import com.tsvigun.workmatestarwars.domain.StarWarsCharacter
import com.tsvigun.workmatestarwars.domain.CharacterRepository
import com.tsvigun.workmatestarwars.domain.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val api: SwapiApi,
    private val dao: CharacterDao
) : CharacterRepository {

    override suspend fun getAllCharacters(): List<StarWarsCharacter> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllCharacters()
                val entities = response.results.map { it.toEntity() }
                dao.insertCharacters(entities)
            } catch (e: Exception) {
                android.util.Log.e("Repo", "Error", e)
            }
            dao.getAllCharacters().map { it.toDomain() }
        }
    }

    override suspend fun getCharacterById(id: String): StarWarsCharacter {
        return withContext(Dispatchers.IO) {
            val entity = dao.getCharacterById(id) ?: throw Exception("Not found")

            val urls = entity.filmsUrls.split(",").filter { it.isNotBlank() }

            val films = try {
                urls.map { url ->
                    val dto = api.getFilmByUrl(url)
                    Film(title = dto.title, openingCrawl = dto.opening_crawl)
                }
            } catch (e: Exception) {
                emptyList()
            }

            entity.toDomain().copy(films = films)
        }
    }
}