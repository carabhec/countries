package com.hcdisat.countrylist.dataaccess.repository

import com.hcdisat.countrylist.dataaccess.exeptions.ApiException
import com.hcdisat.countrylist.dataaccess.exeptions.EmptyBodyException
import com.hcdisat.countrylist.dataaccess.model.Country
import com.hcdisat.countrylist.dataaccess.service.CountryService

interface CountryApiRepository {
    suspend fun getCounties(): Result<List<Country>>
}

class CountryApiRepositoryImpl constructor(
    private val countryService: CountryService
) : CountryApiRepository {
    override suspend fun getCounties(): Result<List<Country>> = runCatching {
        callForCountries()
    }

    private suspend fun callForCountries(): List<Country> {
        val response = countryService.getCountries()
        return if (response.isSuccessful) {
            val body = response.body() ?: throw EmptyBodyException()
            body
        } else {
            throw ApiException(response.code())
        }
    }
}