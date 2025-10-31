package domain.models.common

import java.util.UUID

@JvmInline
value class Id(val raw: String)

object IdGenerator {
    fun generateId(): Id = Id(UUID.randomUUID().toString())
}