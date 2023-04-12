package com.ucne.gestionobrasapp.ui.adelantos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.PagosDto
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.data.repositoy.pagos.PagosApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PagosListState(
    val isLoading: Boolean = false,
    val pagos: List<PagosDto> = emptyList(),
    val error: String = ""
)

data class PagosState(
    val isLoading: Boolean = false,
    val pagos: PagosDto? = null,
    val error: String = ""
)

@HiltViewModel
class PagosApiViewModel @Inject constructor(
    private val pagosApiRepositoryImp: PagosApiRepositoryImp
) : ViewModel() {

    var pagoId by mutableStateOf(0)
    var pagoIdError by mutableStateOf("")

    var fecha by mutableStateOf("")
    var fechaError by mutableStateOf("")

    var personaId by mutableStateOf("")
    var personaIdError by mutableStateOf("")

    var monto by mutableStateOf("")
    var montoError by mutableStateOf("")

    var adelantoId by mutableStateOf("")
    var adelantoError by mutableStateOf("")

    var total by mutableStateOf("")
    var totalError by mutableStateOf("")

    val proyectoId by mutableStateOf("")
    var proyectoIdError by mutableStateOf("")


    var uiState = MutableStateFlow(PagosListState())
        private set
    var uiStatePagos = MutableStateFlow(PagosState())
        private set

    init {
        pagosApiRepositoryImp.getPagos().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(pagos = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun PagosbyId(id: Int) {
        pagoId = id
        Limpiar()
        pagosApiRepositoryImp.getPagosId(pagoId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStatePagos.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStatePagos.update {
                        it.copy(pagos = result.data)
                    }
                    fecha = uiStatePagos.value.pagos!!.fecha
                    personaId = uiStatePagos.value.pagos!!.personaId.toString()
                    monto = uiStatePagos.value.pagos!!.monto.toString()
                    adelantoId = uiStatePagos.value.pagos!!.adelantoId.toString()
                    total = uiStatePagos.value.pagos!!.total.toString()
                }
                is Resource.Error -> {
                    uiStatePagos.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putPagos(id: Int) {
        viewModelScope.launch {
            pagoId = id
            try {
                if (pagoId != null) {
                    pagosApiRepositoryImp.putPagos(
                        pagoId, PagosDto(
                            pagoId = pagoId,
                            fecha = fecha,
                            personaId = uiStatePagos.value.pagos!!.personaId,
                            monto = monto.toDoubleOrNull() ?: 0.0,
                            adelantoId = uiStatePagos.value.pagos!!.proyectoId,
                            total = total.toDoubleOrNull() ?: 0.0,
                            proyectoId = uiStatePagos.value.pagos!!.proyectoId
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

    fun deletePagos(id: Int) {
        viewModelScope.launch {
            pagoId = id!!
            try {
                if (pagoId != null) {
                    pagosApiRepositoryImp.deletePagos(
                        pagoId, PagosDto(
                            pagoId = pagoId,
                            fecha = fecha,
                            personaId = uiStatePagos.value.pagos!!.personaId,
                            monto = monto.toDoubleOrNull() ?: 0.0,
                            adelantoId = uiStatePagos.value.pagos!!.proyectoId,
                            total = total.toDoubleOrNull() ?: 0.0,
                            proyectoId = uiStatePagos.value.pagos!!.proyectoId
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

    fun postPagos() {
        viewModelScope.launch {
            try {
                pagosApiRepositoryImp.postPagos(
                    PagosDto(
                        pagoId = pagoId,
                        fecha = fecha,
                        personaId = personaId,
                        monto = monto.toDoubleOrNull() ?: 0.0,
                        adelantoId = 1,
                        total = total.toDoubleOrNull() ?: 0.0,
                        proyectoId = 1
                    )
                )
                Limpiar()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    fun Limpiar() {
        personaId = ""
        fecha = ""
        monto = ""
        adelantoId = ""
        total = ""

    }


    /* -------------------------------------- Validaciones --------------------------------------- */
    fun onFechaChanged(fecha: String) {
        this.fecha = fecha
        HayErroresRegistrando()
    }

    fun onPersonaIdChanged(personaId: String) {
        this.personaId = personaId
        HayErroresRegistrando()
    }

    fun onMontoChanged(monto: String) {
        this.monto = monto
        HayErroresRegistrando()
    }


    fun HayErroresRegistrando(): Boolean {

        var hayError = false

        fechaError = ""
        if (fecha.isBlank()) {
            hayError = true
        }


        personaIdError = ""
        if (personaId.isBlank()) {
            hayError = true
        }

        montoError = ""
        if (monto.isBlank()) {
            hayError = true
        }

        return hayError
    }
}
