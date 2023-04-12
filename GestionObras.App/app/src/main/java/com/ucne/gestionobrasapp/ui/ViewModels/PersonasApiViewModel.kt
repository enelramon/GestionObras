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

data class TiposListState(
    val isLoading: Boolean = false,
    val tipos: List<TiposDto> = emptyList(),
    val error: String = ""
)
data class TiposState(
    val isLoading: Boolean = false,
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

    var nombres by mutableStateOf("")
    var nombresError by mutableStateOf("")

    var telefono by mutableStateOf("")
    var telefonoError by mutableStateOf("")

    var tiposTrabajo by mutableStateOf("")
    var tiposTrabajoError by mutableStateOf("")
    val tiposDeTrabajo = listOf("Carpintero", "Ingeniero Civil", "Arquitecto", "Proveedor de materiales", "")

    var precio by mutableStateOf("")
    var precioError by mutableStateOf("")

    var uiState = MutableStateFlow(PersonasListState())
        private set
    var uiStatePersonas = MutableStateFlow(PersonasState())
        private set

    var uiStateTipos = MutableStateFlow(TiposListState())
        private set
    var uiStateTiposT = MutableStateFlow(TiposState())
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
                    tiposTrabajo
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
                            proyectoId = 0,
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
                            proyectoId = 0,
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
                personasApiRepositoryImp.deletePersonas(
                    personaId, PersonasDto(
                        personaId = personaId,
                        nombres = nombres,
                        tipoTrabajoId = tipoTrabajoId.toIntOrNull() ?: 0,
                        proyectoId = 0,
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
        precio = ""
        telefono = ""
    }


    /* -------------------------------------- Validaciones --------------------------------------- */
    fun onNombresChanged(nombres: String) {
        this.nombres = nombres
        HayErroresRegistrando()
    }

    fun onTelefonoChanged(telefono: String) {
        this.telefono = telefono
        HayErroresRegistrando()
    }

    fun onTrabajosChanged(tiposTrabajo: String) {
        this.tiposTrabajo = tiposTrabajo
        HayErroresRegistrando()
    }

    fun onPrecioChanged(precio: String) {
        this.precio = precio
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

        fun onTrabajosChanged(tiposTrabajo: String) {
            this.tiposTrabajo = tiposTrabajo
            HayErroresRegistrando()
        }

        precioError = ""
        if (precio.isBlank()) {
            hayError = true
        }

        return hayError
    }
}
