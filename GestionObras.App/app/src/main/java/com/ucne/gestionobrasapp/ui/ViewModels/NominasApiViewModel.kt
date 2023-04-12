package com.ucne.gestionobrasapp.ui.nominas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.AdelantosDto
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.data.repositoy.nominas.NominasApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NominasListState(
    val isLoading: Boolean = false,
    val nominas: List<NominasDto> = emptyList(),
    val error: String = ""
)
data class NominasState(
    val isLoading: Boolean = false,
    val nominas: NominasDto? = null,
    val error: String = ""
)

@HiltViewModel
class NominasApiViewModel @Inject constructor(
    private val nominasApiRepositoryImp: NominasApiRepositoryImp
) : ViewModel() {
    var nominaId by mutableStateOf(0)

    var fechaNomina by mutableStateOf("")
    var fechanominaError by mutableStateOf("")

    var totalnomina by mutableStateOf("")
    var totalnominaError by mutableStateOf("")

    var estadonomina by mutableStateOf("")
    var estadonominaError by mutableStateOf("")
    val tipoestado = listOf("En proceso", "Finalizado")

    var proyectonominaId by mutableStateOf("")
    var proyectonominaIdError by mutableStateOf("")

    var personanominaId by mutableStateOf("")
    var personanominaIdError by mutableStateOf("")

    var uiState = MutableStateFlow(NominasListState())
        private set
    var uiStateNomina = MutableStateFlow(NominasState())
        private set


    init {
        nominasApiRepositoryImp.getNominas().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(nominas = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun NominasbyId(id: Int) {
        nominaId = id
        Limpiar()
        nominasApiRepositoryImp.getNominasId(nominaId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateNomina.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateNomina.update { it.copy(nominas = result.data) }
                    fechaNomina = uiStateNomina.value.nominas!!.fecha
                    personanominaId = uiStateNomina.value.nominas!!.personaId.toString()
                    proyectonominaId = uiStateNomina.value.nominas!!.proyectoId.toString()
                    totalnomina = uiStateNomina.value.nominas!!.total.toString()
                    estadonomina = uiStateNomina.value.nominas!!.estado
                }
                is Resource.Error -> {
                    uiStateNomina.update { it.copy(error = result.message ?: "error desconocido") }
                }
            }
        }

    }

    fun postNominas() {
        viewModelScope.launch {
            try {
                nominasApiRepositoryImp.postNominas(
                    NominasDto(
                        nominaId = nominaId,
                        fecha = fechaNomina,
                        personaId = personanominaId.toIntOrNull() ?: 0,
                        proyectoId =proyectonominaId.toIntOrNull() ?: 0 ,
                        total = totalnomina.toDoubleOrNull() ?: 0.0,
                        estado = estadonomina
                    )
                )
                Limpiar()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    fun putNominas(id: Int) {
        viewModelScope.launch {
            nominaId = id
            try {
                if (nominaId != null) {
                    nominasApiRepositoryImp.putNominas(
                        nominaId, NominasDto(
                            nominaId = nominaId,
                            fecha = fechaNomina,
                            personaId = personanominaId.toIntOrNull() ?: 0,
                            proyectoId =proyectonominaId.toIntOrNull() ?: 0 ,
                            total = totalnomina.toDoubleOrNull() ?: 0.0,
                            estado = estadonomina
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

    private fun Limpiar() {
        fechaNomina = ""
        totalnomina = ""
        estadonomina = ""
        proyectonominaId = ""
        personanominaId = ""
    }

    fun onFechaChanged(fecha: String) {
        this.fechaNomina = fecha
        HayErroresRegistrando()
    }

    fun ontotalCanged(Total: String) {
        this.totalnomina = Total
        HayErroresRegistrando()
    }

    fun onProyectoIdCanged(proyectoId: String) {
        this.proyectonominaId = proyectoId
        HayErroresRegistrando()
    }

    fun onPersonaIdCanged(personaId: String) {
        this.personanominaId = personaId
        HayErroresRegistrando()
    }

    fun onEstadoCanged(estado: String) {
        this.estadonomina = estado
        HayErroresRegistrando()
    }

    fun HayErroresRegistrando(): Boolean {

        var hayError = false

        fechanominaError = ""
        if (fechaNomina.isBlank()) {
            hayError = true
        }

        totalnominaError = ""
        if (totalnomina.isBlank()) {
            hayError = true
        }

        proyectonominaIdError = ""
        if (proyectonominaId.isBlank()) {
            hayError = true
        }

        estadonominaError = ""
        if (estadonomina.isBlank()) {
            hayError = true
        }
        personanominaIdError = ""
        if (personanominaId.isBlank()) {
            hayError = true
        }

        return hayError
    }

    fun Clean() {
        fechaNomina = ""
        totalnomina = ""
        estadonomina = ""
        proyectonominaId = ""
        personanominaId = ""
    }
}