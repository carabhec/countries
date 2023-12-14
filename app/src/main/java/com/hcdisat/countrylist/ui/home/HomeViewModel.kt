package com.hcdisat.countrylist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hcdisat.countrylist.core.ServiceLocatorImpl
import com.hcdisat.countrylist.domain.repository.ErrorMapper
import com.hcdisat.countrylist.domain.usecases.GetCountriesUseCase
import com.hcdisat.countrylist.ui.home.model.HomeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getCountries: GetCountriesUseCase,
    private val errorMapper: ErrorMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val state = _state.asStateFlow()

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            _state.value = withContext(dispatcher) { getCountries() }.fold(
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
        val Factory: ViewModelProvider.Factory by lazy {
            val serviceLocator = ServiceLocatorImpl.instance
            viewModelFactory {
                initializer {
                    HomeViewModel(
                        getCountries = serviceLocator.locateGetCountriesUseCase(),
                        dispatcher = serviceLocator.locateIODispatcher(),
                        errorMapper = serviceLocator.locateErrorMapper()
                    )
                }
            }
        }
    }
}