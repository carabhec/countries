package com.hcdisat.countrylist.core

import com.hcdisat.countrylist.dataaccess.DataAccessServiceProviderImpl
import com.hcdisat.countrylist.dataaccess.service.CountryService
import com.hcdisat.countrylist.dataaccess.service.RetrofitBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.reflect.KClass

interface ServiceLocator {
    fun resolveCountryService(): CountryService
    fun getIODispatcher(): CoroutineDispatcher
}

data class ServiceItem<T: ServiceProvider<Any>>(val key: KClass<T>, val item: )

class ServiceLocatorImpl private constructor() : ServiceLocator {
    private val providers: Map<KClass<T>, T>? = null
    override fun resolveCountryService(): CountryService = RetrofitBuilder.countryService

    override fun getIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    companion object {
        private var _instance: ServiceLocatorImpl? = null
        val instance get() = _instance ?: ServiceLocatorImpl().also { _instance = it }
    }
}