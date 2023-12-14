package com.hcdisat.countrylist.core

fun interface ServiceProvider<out T> {
    fun provide(): T
}