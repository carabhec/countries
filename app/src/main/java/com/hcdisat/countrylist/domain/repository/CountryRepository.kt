package com.hcdisat.countrylist.domain.repository

import com.hcdisat.countrylist.dataaccess.repository.CountryApiRepository
import com.hcdisat.countrylist.domain.model.DomainCountry
import com.hcdisat.countrylist.domain.model.toDomainCountry

interface CountryRepository {
    suspend fun getCountries(): Result<List<DomainCountry>>
}

class CountryRepositoryImpl constructor(
    private val apiRepository: CountryApiRepository
): CountryRepository {
    override suspend fun getCountries(): Result<List<DomainCountry>> {
        return apiRepository.getCounties().mapCatching { countriesResult ->
            countriesResult.map { it.toDomainCountry() }
        }
    }
}