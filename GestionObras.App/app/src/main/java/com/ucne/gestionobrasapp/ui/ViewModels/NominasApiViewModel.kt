package com.ucne.gestionobrasapp.ui.nominas

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

    fun NominasbyId(id: Int) {}

    fun putNominas(id: Int) {}

    fun deleteNominas(id: Int) {}

    fun postNominas() {}

    private fun Limpiar() {}
}
