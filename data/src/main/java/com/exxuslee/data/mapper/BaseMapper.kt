package com.exxuslee.data.mapper

interface BaseMapper<D, L, R> {

    fun domainToLocal(type: D): L

    fun localToDomain(type: L): D

    fun remoteToLocal(type: R): L

    fun remoteToDomain(type: R): D

}