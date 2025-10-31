package domain.models

import domain.models.common.Id

data class Category(
    val id: Id,
    var type: CategoryType,
    var name: String
)