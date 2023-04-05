package com.ucne.gestionobrasapp.ui.personas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.local.entity.TiposEntity
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto
import com.ucne.gestionobrasapp.data.repositoy.personas.PersonasApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.tipos.TiposApiRepositoryImp
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

    var nombres by mutableStateOf("")
    var nombresError by mutableStateOf("")

    var telefono by mutableStateOf("")
    var telefonoError by mutableStateOf("")

    var tiposTrabajo by mutableStateOf("")

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
                    precio = uiStatePersonas.value.personas!!.precio.toString()
                }
                is Resource.Error -> {
                    uiStatePersonas.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putPersonas(id: Int) {
        viewModelScope.launch {
            personaId = id!!
            try {
                if (personaId != null) {
                    personasApiRepositoryImp.putPersonas(
                        personaId, PersonasDto(
                            personaId = personaId,
                            nombres = nombres,
                            tipoTrabajoId = tiposTrabajo.toIntOrNull()?:0,
                            precio = precio.toDoubleOrNull()?:0.0,
                            telefono = telefono
                        )
                    )
                    Limpiar()
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
            personasApiRepositoryImp.postPersonas(
                PersonasDto(
                    personaId = personaId,
                    nombres = nombres,
                    tipoTrabajoId = uiStatePersonas.value.tipos!!.tipoTrabajoId,
                    precio = precio.toDoubleOrNull()?:0.0,
                    telefono = telefono
                )
            )
            Limpiar()
        }
    }

    private fun Limpiar() {
        nombres = ""
        tiposTrabajo = ""
        precio = ""
        telefono = ""
    }


    /* -------------------------------------- Validaciones --------------------------------------- */
    fun onNombresChanged(nombres: String) {
        this.nombres = nombres
        HayErrores()
    }

    fun onPrecioChanged(precio: String) {
        this.precio = precio
        HayErrores()
    }

    fun onTelefonoChanged(telefono: String) {
        this.telefono = telefono
        HayErrores()
    }

    fun HayErrores(): Boolean {

        var hayError = false

        nombresError = ""

        if (nombres.isNullOrBlank()) {
            nombresError = "Ingrese el nombre de la persona"
            hayError = true
        }

        precioError = ""
        if (precio.isNullOrBlank()) {
            precioError = "Ingrese un precio"
            hayError = true
        }

        telefonoError = ""
        if(telefono.isNullOrBlank()){
            telefonoError = "Ingrese un numero telefonico"
            hayError = true
        }

        return hayError
    }
}
