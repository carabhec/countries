package com.hcdisat.countrylist.ui.model

import com.hcdisat.countrylist.domain.model.DomainCountry

sealed class HomeState(open val countries: List<DomainCountry> = listOf()) {
    data object Loading: HomeState()
    data class Completed(override val countries: List<DomainCountry>): HomeState(countries)
    data class Error(val throwable: Throwable): HomeState()
}