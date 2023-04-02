package com.ucne.gestionobrasapp.ui.adelantos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.AdelantosDto
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto
import com.ucne.gestionobrasapp.data.repositoy.adelantos.AdelantosApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.tipos.TiposApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
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

    fun AdelantosbyId(id: Int) {}

    fun putAdelantos(id: Int) {}

    fun deleteAdelantos(id: Int) {}

    fun postAdelantos() {}

    private fun Limpiar() {}
}
