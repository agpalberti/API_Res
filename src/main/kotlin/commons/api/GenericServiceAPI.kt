package com.dam.api.commons.api

import java.io.Serializable

interface GenericServiceAPI<T, ID: Serializable> {
    /**
     * Inserta 1 registro en la tabla
     */
    fun insertOne(entity: T): T

    /**
     * Elimina 1 registro de la tabla
     */
    fun deleteOne(id: ID)

    /**
     * Obtiene 1 registro de la tabla
     */
    operator fun get(id: ID): T?

    /**
     * Obtiene todos los registros de la tabla
     */
    val all: MutableList<T>?
}