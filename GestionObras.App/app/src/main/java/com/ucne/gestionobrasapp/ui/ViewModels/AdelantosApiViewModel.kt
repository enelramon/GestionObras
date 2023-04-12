package com.ucne.gestionobrasapp.ui.adelantos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.AdelantosDto
import com.ucne.gestionobrasapp.data.remote.dto.PagosDto
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto
import com.ucne.gestionobrasapp.data.repositoy.adelantos.AdelantosApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.tipos.TiposApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AdelantosListState(
    val isLoading: Boolean = false,
    val adelantos: List<AdelantosDto> = emptyList(),
    val error: String = ""
)
data class AdelantosState(
    val isLoading: Boolean = false,
    val adelantos: AdelantosDto? = null,
    val error: String = ""
)
@HiltViewModel
class AdelantosApiViewModel @Inject constructor(
    private val adelantosApiRepositoryImp: AdelantosApiRepositoryImp
) : ViewModel() {

    var adelantoId by mutableStateOf(0)

    var fecha by mutableStateOf("2023-04-01")
    var fechaError by mutableStateOf("")

    var personaId by mutableStateOf("")
    var personaIdError by mutableStateOf("")

    var monto by mutableStateOf("")
    var montoError by mutableStateOf("")

    var balance by mutableStateOf("")
    var balanceError by mutableStateOf("")

    var uiState = MutableStateFlow(AdelantosListState())
        private set
    var uiStateAdelantos = MutableStateFlow(AdelantosState())
        private set

    init {
        adelantosApiRepositoryImp.getAdelantos().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(adelantos = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun AdelantosbyId(id: Int) {
        adelantoId = id
        Limpiar()
        adelantosApiRepositoryImp.getAdelantosId(adelantoId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateAdelantos.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateAdelantos.update {
                        it.copy(adelantos = result.data)
                    }
                    fecha = uiStateAdelantos.value.adelantos!!.fecha
                    personaId = uiStateAdelantos.value.adelantos!!.personaId.toString()
                    monto = uiStateAdelantos.value.adelantos!!.monto.toString()
                    balance = uiStateAdelantos.value.adelantos!!.balance.toString()
                }
                is Resource.Error -> {
                    uiStateAdelantos.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putAdelantos(id: Int) {
        viewModelScope.launch {
            adelantoId = id
            try {
                if (adelantoId != null) {
                    adelantosApiRepositoryImp.putAdelantos(
                        adelantoId, AdelantosDto(
                            adelantoId = uiStateAdelantos.value.adelantos!!.adelantoId,
                            pagoId = uiStateAdelantos.value.adelantos!!.pagoId,
                            fecha = fecha,
                            personaId = personaId.toIntOrNull() ?: 0,
                            monto = monto.toDoubleOrNull() ?: 0.0,
                            balance = balance.toDoubleOrNull() ?: 0.0,
                            proyectoId = uiStateAdelantos.value.adelantos!!.proyectoId
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

    fun deleteAdelantos(id: Int) {
        viewModelScope.launch {
            adelantoId = id!!
            try {
                if (adelantoId != null) {
                    adelantosApiRepositoryImp.deleteAdelantos(
                        adelantoId, AdelantosDto(
                            adelantoId = uiStateAdelantos.value.adelantos!!.adelantoId,
                            pagoId = uiStateAdelantos.value.adelantos!!.pagoId,
                            fecha = fecha,
                            personaId = personaId.toIntOrNull() ?: 0,
                            monto = monto.toDoubleOrNull() ?: 0.0,
                            balance = balance.toDoubleOrNull() ?: 0.0,
                            proyectoId = uiStateAdelantos.value.adelantos!!.adelantoId
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

    fun postAdelantos() {
        viewModelScope.launch {
            try {
                adelantosApiRepositoryImp.postAdelantos(
                    AdelantosDto(
                        adelantoId = uiStateAdelantos.value.adelantos!!.adelantoId,
                        pagoId = uiStateAdelantos.value.adelantos!!.pagoId,
                        fecha = "2023-04-01",
                        personaId = personaId.toIntOrNull() ?: 0,
                        monto = monto.toDoubleOrNull() ?: 0.0,
                        balance = balance.toDoubleOrNull() ?: 0.0,
                        proyectoId = uiStateAdelantos.value.adelantos!!.adelantoId
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
        balance = ""
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

    fun onBalanceChanged(balance: String) {
        this.balance = balance
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

        balanceError = ""
        if (balance.isBlank()) {
            hayError = true
        }

        return hayError
    }
}
