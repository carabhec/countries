package com.hcdisat.countrylist.dataaccess.service

import com.hcdisat.countrylist.dataaccess.ServiceConstants
import com.hcdisat.countrylist.dataaccess.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {
    @GET(ServiceConstants.PATH)
    suspend fun getCountries(): Response<List<Country>>
}