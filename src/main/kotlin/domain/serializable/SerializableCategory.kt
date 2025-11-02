package domain.serializable

import domain.models.Category
import domain.models.CategoryType
import domain.models.common.toId

@kotlinx.serialization.Serializable
data class SerializableCategory(
    val id: String,
    val type: String,
    val name: String
) {
    fun toDomain(): Category = Category(id.toId(), CategoryType.valueOf(type), name)
}

