package domain.models.common

import java.util.*

@JvmInline
value class Id(val raw: String)

object IdGenerator {
    fun generateId(): Id = Id(UUID.randomUUID().toString())
}

fun String.toId(): Id = Id(this)