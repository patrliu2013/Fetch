package com.example.base

/**
 * Base Mapper to Map Api Response to Domain Models
 * */
interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}