package com.hcdisat.countrylist.dataaccess

import com.hcdisat.countrylist.core.ServiceProvider
import com.hcdisat.countrylist.dataaccess.service.CountryService
import com.hcdisat.countrylist.dataaccess.service.RetrofitBuilder

class DataAccessServiceProviderImpl: ServiceProvider<CountryService> {
    override fun provide(): CountryService {
        return RetrofitBuilder.countryService
    }
}