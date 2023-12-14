package com.hcdisat.countrylist.domain.repository

import com.hcdisat.countrylist.dataaccess.exeptions.ApiException
import com.hcdisat.countrylist.dataaccess.exeptions.NoInternetException
import com.hcdisat.countrylist.dataaccess.exeptions.UnexpectedException
import okio.IOException
import retrofit2.HttpException

interface ErrorMapper {
    fun map(exception: Throwable): Exception
}

class ErrorMapperImpl : ErrorMapper {
    override fun map(throwable: Throwable): Exception {
        return when (throwable) {
            is IOException -> NoInternetException()
            is HttpException -> ApiException(throwable.code())
            else -> UnexpectedException(throwable)
        }
    }
}