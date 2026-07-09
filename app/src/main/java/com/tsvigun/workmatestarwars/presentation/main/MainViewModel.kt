package com.tsvigun.workmatestarwars.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsvigun.workmatestarwars.domain.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {

        viewModelScope.launch {

            _state.value = MainState.Loading

            try {

                val characters = repository.getAllCharacters()

                if (characters.isEmpty()) {
                    _state.value = MainState.Error("Empty data")
                } else {
                    _state.value = MainState.Success(characters)
                }
            } catch (e: Exception) {
                _state.value = MainState.Error("Loading Error")
            }


        }

    }

}