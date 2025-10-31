package domain.repository

import domain.models.common.Id

interface Repository<T> {
    fun findById(id: Id): T?
    fun findAll(): List<T>
    fun save(entity: T): Boolean
    fun update(entity: T): Boolean
    fun delete(id: Id): Boolean
}