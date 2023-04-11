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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProyectosListState(
    val isLoading: Boolean = false,
    val proyectos: List<ProyectosDto> = emptyList(),
    val error: String = ""
)
data class ProyectosState(
    val isLoading: Boolean = false,
    val proyectos: ProyectosDto? = null,
    val error: String = ""
)
@HiltViewModel
class ProyectosApiViewModel @Inject constructor(
    private val proyectosApiRepositoryImp: ProyectosApiRepositoryImp
) : ViewModel() {

    var proyectoId by mutableStateOf(0)

    var descripcion by mutableStateOf("")
    var descripcionError by mutableStateOf("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    var uiState = MutableStateFlow(ProyectosListState())
        private set
    var uiStateProyectos = MutableStateFlow(ProyectosState())
        private set

    fun onDescripcionChanged(descripcion: String) {
        this.descripcion = descripcion
        HayErroresRegistrando()
    }

    fun HayErroresRegistrando(): Boolean {

        var hayError = false

        descripcionError = ""
        if (descripcion.isBlank()) {
            hayError = true
        }

        return hayError
    }


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
        reload()
    }

    fun reload(){
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000L)
            _isLoading.value = false
        }
    }

    fun ProyectosbyId(id: Int) {
        proyectoId = id
        Limpiar()
        proyectosApiRepositoryImp.getProyectosId(proyectoId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateProyectos.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateProyectos.update {
                        it.copy(proyectos = result.data)
                    }
                    descripcion = uiStateProyectos.value.proyectos!!.descripcion
                }
                is Resource.Error -> {
                    uiStateProyectos.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putProyectos(id: Int) {
        viewModelScope.launch {
            proyectoId = id!!
            try {
                if (proyectoId != null) {
                    proyectosApiRepositoryImp.putProyectos(
                        proyectoId, ProyectosDto(
                            descripcion = descripcion,
                            proyectoId = proyectoId
                        )
                    )
                } else {
                    throw NullPointerException("Value is null")
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    fun deleteProyectos(id: Int?) {
        id?.let {
            viewModelScope.launch {
                try {
                    proyectosApiRepositoryImp.deleteProyectos(id)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } ?: kotlin.run {
            throw NullPointerException("Value is null")
        }
    }

    fun postProyectos() {
        viewModelScope.launch {
            try {
                proyectosApiRepositoryImp.postProyectos(
                    ProyectosDto(
                        descripcion = descripcion,
                        proyectoId = proyectoId
                    )
                )
                Limpiar()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    private fun Limpiar() {}
}
