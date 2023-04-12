package com.ucne.gestionobrasapp.ui.personas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto
import com.ucne.gestionobrasapp.data.repositoy.personas.PersonasApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PersonasListState(
    val isLoading: Boolean = false,
    val personas: List<PersonasDto> = emptyList(),
    val tipos: List<TiposDto> = emptyList(),
    val error: String = ""
)

data class PersonasState(
    val isLoading: Boolean = false,
    val personas: PersonasDto? = null,
    val tipos: TiposDto? = null,
    val error: String = ""
)

@HiltViewModel
class PersonasApiViewModel @Inject constructor(
    private val personasApiRepositoryImp: PersonasApiRepositoryImp
) : ViewModel() {

    /* Creación de metodos para optener datos de la persona*/

    var personaId by mutableStateOf(0)
    var tipoTrabajoId by mutableStateOf("")
    var tipoIdError by mutableStateOf("")

    var nombres by mutableStateOf("")
    var nombresError by mutableStateOf("")

    var telefono by mutableStateOf("")
    var telefonoError by mutableStateOf("")

    var projectoId by mutableStateOf("")
    var projectoIdError by mutableStateOf("")

    var uiState = MutableStateFlow(PersonasListState())
        private set
    var uiStatePersonas = MutableStateFlow(PersonasState())
        private set

    var personasL = personasApiRepositoryImp.getPersonas()
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

    fun setPersona(id: Int) {
        personaId = id
        Limpiar()
        personasApiRepositoryImp.getPersonasId(personaId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStatePersonas.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStatePersonas.update {
                        it.copy(personas = result.data)
                    }
                    nombres = uiStatePersonas.value.personas!!.nombres
                    telefono = uiStatePersonas.value.personas!!.telefono
                    projectoId = uiStatePersonas.value.personas!!.proyectoId.toString()
                    tipoTrabajoId = uiStatePersonas.value.personas!!.tipoTrabajoId.toString()

                }
                is Resource.Error -> {
                    uiStatePersonas.update {
                        it.copy(
                            error = result.message ?: "Error desconocido"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun PersonasbyId(id: Int) {
        personaId = id
        Limpiar()
        personasApiRepositoryImp.getPersonasId(personaId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStatePersonas.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStatePersonas.update {
                        it.copy(personas = result.data)
                    }
                    nombres = uiStatePersonas.value.personas!!.nombres
                    telefono = uiStatePersonas.value.personas!!.telefono
                    projectoId = uiStatePersonas.value.personas!!.proyectoId.toString()
                    tipoTrabajoId = uiStatePersonas.value.personas!!.tipoTrabajoId.toString()

                }
                is Resource.Error -> {
                    uiStatePersonas.update {
                        it.copy(
                            error = result.message ?: "Error desconocido"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putPersonas(id: Int) {
        viewModelScope.launch {
            personaId = id
            try {
                if (personaId != null) {
                    personasApiRepositoryImp.putPersonas(
                        personaId, PersonasDto(
                            personaId = personaId,
                            nombres = nombres,
                            uiStatePersonas.value.personas!!.tipoTrabajoId,
                            uiStatePersonas.value.personas!!.proyectoId,
                            telefono = telefono
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

    fun deletePersonas(id: Int) {
        viewModelScope.launch {
            personaId = id!!
            try {
                if (personaId != null) {
                    personasApiRepositoryImp.deletePersonas(
                        personaId, PersonasDto(
                            personaId = personaId,
                            nombres = nombres,
                            uiStatePersonas.value.personas!!.tipoTrabajoId,
                            uiStatePersonas.value.personas!!.proyectoId,
                            telefono = telefono

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

    fun postPersonas() {
        viewModelScope.launch {
            try {
                personasApiRepositoryImp.postPersonas(
                     PersonasDto(
                        personaId = personaId,
                        nombres = nombres,
                        tipoTrabajoId = tipoTrabajoId.toIntOrNull() ?: 0,
                        proyectoId = projectoId.toIntOrNull() ?: 0,
                        telefono = telefono
                    )
                )
                Limpiar()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    fun Limpiar() {
        nombres = ""
        tipoTrabajoId = ""
        projectoId = ""
        telefono = ""
    }


    /* -------------------------------------- Validaciones --------------------------------------- */
    fun onNombresChanged(nombres: String) {
        this.nombres = nombres
        HayErroresRegistrando()
    }

    fun onTipoChanged(tipo: String) {
        this.tipoTrabajoId = tipo
        HayErroresRegistrando()
    }

    fun onProjecChanged(project: String) {
        this.projectoId = project
        HayErroresRegistrando()
    }

    fun onTelefonoChanged(telefono: String) {
        this.telefono = telefono
        HayErroresRegistrando()
    }


    fun HayErroresRegistrando(): Boolean {

        var hayError = false

        nombresError = ""
        if (nombres.isBlank()) {
            hayError = true
        }

        telefonoError = ""
        if (telefono.isBlank()) {
            hayError = true
        }

        tipoIdError = ""
        if (tipoTrabajoId.isBlank()) {
            hayError = true
        }
        projectoIdError = ""
        if (projectoId.isBlank()) {
            hayError = true
        }

        return hayError
    }
}
