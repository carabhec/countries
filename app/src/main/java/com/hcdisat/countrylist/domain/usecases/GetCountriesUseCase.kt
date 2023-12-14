package com.hcdisat.countrylist.domain.usecases

import com.hcdisat.countrylist.domain.model.DomainCountry
import com.hcdisat.countrylist.domain.repository.CountryRepository

interface GetCountriesUseCase {
    suspend operator fun invoke(): Result<List<DomainCountry>>
}

class GetCountriesUseCaseImpl constructor(
    private val countryRepository: CountryRepository
) : GetCountriesUseCase {
    override suspend fun invoke(): Result<List<DomainCountry>> =
        countryRepository.getCountries().mapCatching { it }
}