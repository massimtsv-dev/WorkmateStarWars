package com.tsvigun.workmatestarwars.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsvigun.workmatestarwars.domain.CharacterRepository
import com.tsvigun.workmatestarwars.domain.StarWarsCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _character = MutableStateFlow<StarWarsCharacter?>(null)
    val character: StateFlow<StarWarsCharacter?> = _character.asStateFlow()

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            try {
                _character.value = repository.getCharacterById(id)
            } catch (e: Exception) {
                android.util.Log.e("DetailViewModel", "Error loading character", e)
            }
        }
    }
}