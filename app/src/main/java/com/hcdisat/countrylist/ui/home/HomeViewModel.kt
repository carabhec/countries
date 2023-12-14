package com.hcdisat.countrylist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcdisat.countrylist.domain.repository.ErrorMapper
import com.hcdisat.countrylist.domain.usecases.GetCountriesUseCase
import com.hcdisat.countrylist.ui.model.HomeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val getCountries: GetCountriesUseCase,
    private val errorMapper: ErrorMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState.Loading)
    val state = _state.asStateFlow()

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch(dispatcher) {
            getCountries().fold(
                onSuccess = {
                    HomeState.Completed(it)
                },
                onFailure = {
                    HomeState.Error(errorMapper.map(it))
                }
            )
        }
    }

    companion object {
//        val Factory: ViewModelProvider.Factory by lazy {
//            viewModelFactory {
//                initializer {
//                    HomeViewModel()
//                }
//            }
//        }
    }
}