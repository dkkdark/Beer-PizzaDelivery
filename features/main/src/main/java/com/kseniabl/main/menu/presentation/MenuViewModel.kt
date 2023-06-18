package com.kseniabl.main.menu.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kseniabl.main.menu.entity.BeerModel
import com.kseniabl.main.menu.usecases.MenuUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuUseCases: MenuUseCases
): ViewModel() {

    private val _beerList = MutableStateFlow<List<BeerModel>?>(null)
    val beerList = _beerList.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun getBeer() {
        viewModelScope.launch {
            menuUseCases.getBeerUseCase()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    Log.e("MenuViewModel", "error $e")
                    _error.emit("Error: ${e.message}")
                }
                .collect {
                    _beerList.emit(it)
                }
        }
    }
}