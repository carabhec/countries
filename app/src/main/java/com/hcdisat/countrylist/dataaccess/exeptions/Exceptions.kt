package com.hcdisat.countrylist.dataaccess.exeptions

data class ApiException(val code: Int): RuntimeException()
class NoInternetException: RuntimeException()
class EmptyBodyException: RuntimeException()
class UnexpectedException(cause: Throwable): Exception(cause)