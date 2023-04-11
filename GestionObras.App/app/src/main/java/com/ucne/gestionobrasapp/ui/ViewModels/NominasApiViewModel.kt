package com.ucne.gestionobrasapp.ui.nominas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto
import com.ucne.gestionobrasapp.data.repositoy.nominas.NominasApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
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
    var nominaIdError by mutableStateOf("")

    var fechaNomina by mutableStateOf("")
    var fechanominaError by mutableStateOf("")

    var totalnomina by mutableStateOf("")
    var totalnominaError by mutableStateOf("")

    var estadonomina by mutableStateOf("")
    var estadonominaError by mutableStateOf("")

    var proyectonominaId by mutableStateOf("")
    var proyectoIdError by mutableStateOf("")

    var uiState = MutableStateFlow(NominasListState())
        private set
    var uiStateNominas = MutableStateFlow(NominasState())
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
                    uiStateNominas.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateNominas.update { it.copy(nominas = result.data) }
                    fechaNomina = uiStateNominas.value.nominas!!.fecha
                    totalnomina = uiStateNominas.value.nominas!!.total.toString()
                    estadonomina = uiStateNominas.value.nominas!!.estado
                    proyectonominaId = uiStateNominas.value.nominas!!.proyectoId.toString()
                }
                is Resource.Error ->{
                    uiStateNominas.update { it.copy(error = result.message ?: "error desconocido") }
                }
            }
        }

    }

    fun putNominas(id: Int) {}

    fun deleteNominas(id: Int) {}

    fun postNominas() {}

    private fun Limpiar() {}
}
