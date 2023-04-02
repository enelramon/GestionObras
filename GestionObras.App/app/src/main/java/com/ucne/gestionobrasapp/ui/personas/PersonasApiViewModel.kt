package com.ucne.gestionobrasapp.ui.personas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.data.repositoy.personas.PersonasApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class PersonasListState(
    val isLoading: Boolean = false,
    val personas: List<PersonasDto> = emptyList(),
    val error: String = ""
)
data class PersonasState(
    val isLoading: Boolean = false,
    val personas: PersonasDto? = null,
    val error: String = ""
)
@HiltViewModel
class PersonasApiViewModel @Inject constructor(
    private val personasApiRepositoryImp: PersonasApiRepositoryImp
) : ViewModel() {

    var uiState = MutableStateFlow(PersonasListState())
        private set
    var uiStatePersonas = MutableStateFlow(PersonasState())
        private set

    init {
       personasApiRepositoryImp.getPersonas().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(personas = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun PersonasbyId(id: Int) {}

    fun putPersonas(id: Int) {}

    fun deletePersonas(id: Int) {}

    fun postPersonas() {}

    private fun Limpiar() {}
}
