package com.ucne.gestionobrasapp.ui.tipos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto
import com.ucne.gestionobrasapp.data.repositoy.tipos.TiposApiRepositoryImp
import com.ucne.gestionobrasapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

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
class TiposApiViewModel @Inject constructor(
    private val tiposApiRepositoryImp: TiposApiRepositoryImp
) : ViewModel() {

    var uiState = MutableStateFlow(TiposListState())
        private set
    var uiStateTipos = MutableStateFlow(TiposState())
        private set

    init {
       tiposApiRepositoryImp.getTipos().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(tipos = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun TiposbyId(id: Int) {}

    fun putTipos(id: Int) {}

    fun deleteTipos(id: Int) {}

    fun postTipos() {}

    private fun Limpiar() {}
}
