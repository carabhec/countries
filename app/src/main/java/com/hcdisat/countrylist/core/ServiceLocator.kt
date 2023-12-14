package com.hcdisat.countrylist.core

import com.hcdisat.countrylist.dataaccess.repository.CountryApiRepository
import com.hcdisat.countrylist.dataaccess.repository.CountryApiRepositoryImpl
import com.hcdisat.countrylist.dataaccess.service.CountryService
import com.hcdisat.countrylist.dataaccess.service.RetrofitBuilder
import com.hcdisat.countrylist.domain.repository.CountryRepository
import com.hcdisat.countrylist.domain.repository.CountryRepositoryImpl
import com.hcdisat.countrylist.domain.repository.ErrorMapper
import com.hcdisat.countrylist.domain.repository.ErrorMapperImpl
import com.hcdisat.countrylist.domain.usecases.GetCountriesUseCase
import com.hcdisat.countrylist.domain.usecases.GetCountriesUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface ServiceLocator {
    fun locateCountryService(): CountryService
    fun locateIODispatcher(): CoroutineDispatcher
    fun locateGetCountriesUseCase(): GetCountriesUseCase
    fun locateCountryRepository(): CountryRepository
    fun locateCountryApiRepository(): CountryApiRepository
    fun locateErrorMapper(): ErrorMapper
}

class ServiceLocatorImpl private constructor() : ServiceLocator {
    companion object {
        private var _instance: ServiceLocatorImpl? = null
        val instance get() = _instance ?: ServiceLocatorImpl().also { _instance = it }
    }

    override fun locateCountryService(): CountryService = RetrofitBuilder.countryService

    override fun locateIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    override fun locateGetCountriesUseCase(): GetCountriesUseCase {
        return GetCountriesUseCaseImpl(
            countryRepository = locateCountryRepository()
        )
    }

    override fun locateCountryRepository(): CountryRepository {
        return CountryRepositoryImpl(
            apiRepository = locateCountryApiRepository()
        )
    }

    override fun locateCountryApiRepository(): CountryApiRepository {
        return CountryApiRepositoryImpl(
            countryService = locateCountryService()
        )
    }

    override fun locateErrorMapper(): ErrorMapper = ErrorMapperImpl()
}