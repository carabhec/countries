package com.hcdisat.countrylist.domain.model

import com.hcdisat.countrylist.dataaccess.model.Country

data class DomainCountry(
    val name: String = "",
    val region: String = "",
    val code: String = "",
    val capital: String = "",
)

fun Country.toDomainCountry() = DomainCountry(
    name = name.orEmpty(),
    region = region.orEmpty(),
    code = code.orEmpty(),
    capital = capital.orEmpty()
)