package com.ucne.gestionobrasapp.ui.proyectos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.ProyectosDto
import com.ucne.gestionobrasapp.data.repositoy.proyectos.ProyectosApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProyectosListState(
    val isLoading: Boolean = false,
    val proyectos: List<ProyectosDto> = emptyList(),
    val error: String = ""
)
data class ProyectosState(
    val isLoading: Boolean = false,
    val ticket: ProyectosDto? = null,
    val error: String = ""
)
@HiltViewModel
class ProyectosApiViewModel @Inject constructor(
    private val proyectosApiRepositoryImp: ProyectosApiRepositoryImp
) : ViewModel() {

    var uiState = MutableStateFlow(ProyectosListState())
        private set
    var uiStateProyectos = MutableStateFlow(ProyectosState())
        private set

    init {
        proyectosApiRepositoryImp.getProyectos().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(proyectos = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun ProyectosbyId(id: Int) {}

    fun putProyectos(id: Int) {}

    fun deleteProyectos(id: Int) {}

    fun postProyectos() {}

    private fun Limpiar() {}
}
