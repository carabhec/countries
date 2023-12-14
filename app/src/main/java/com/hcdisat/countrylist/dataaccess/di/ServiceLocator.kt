package com.hcdisat.countrylist.dataaccess.di

import com.hcdisat.countrylist.dataaccess.service.CountryService
import com.hcdisat.countrylist.dataaccess.service.RetrofitBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface ServiceLocator {
    fun resolveCountryService(): CountryService
    fun getIODispatcher(): CoroutineDispatcher
}

class ServiceLocatorImpl private constructor() : ServiceLocator {
    override fun resolveCountryService(): CountryService = RetrofitBuilder.countryService

    override fun getIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    companion object {
        private var _instance: ServiceLocatorImpl? = null
        val instance get() = _instance ?: ServiceLocatorImpl().also { _instance = it }
    }
}